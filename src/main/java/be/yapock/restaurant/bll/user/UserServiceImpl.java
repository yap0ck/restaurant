package be.yapock.restaurant.bll.user;

import be.yapock.restaurant.dal.models.User;
import be.yapock.restaurant.dal.repositories.UserRepository;
import be.yapock.restaurant.pl.config.security.JWTProvider;
import be.yapock.restaurant.pl.models.user.AuthDTO;
import be.yapock.restaurant.pl.models.user.LoginForm;
import be.yapock.restaurant.pl.models.user.UserDTO;
import be.yapock.restaurant.pl.models.user.UserForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JWTProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserForm form) {
        if (form == null) throw new IllegalArgumentException("form ne peut être null");
        User user = User.builder()
                .firstName(form.firstName())
                .lastName(form.lastName())
                .login(form.login())
                .password(passwordEncoder.encode(form.password()))
                .build();
        userRepository.save(user);
    }

    @Override
    public AuthDTO login(LoginForm form) {
        if (form==null) throw new IllegalArgumentException("form peut pas être null");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.login(),form.password()));
        User user = userRepository.findByLogin(form.login()).orElseThrow(()->new UsernameNotFoundException("utilisateur pas trouvé"));
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthDTO(user.getLogin(), token);
    }

    @Override
    public User getOne(long id) {
        return userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("utilisateur pas trouvé"));

    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(UserForm form, long id, Authentication authentication) {
        User userConnected = userRepository.findByLogin(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("utilisateur non trouvé"));
        if (form==null) throw new IllegalArgumentException("form peut pas être null");
        if (!userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("utilisateur non trouvé")).getLogin().equals(userConnected.getLogin())) throw new BadCredentialsException("acces non authorisé");
        User user = getOne(id);
        user.setFirstName(form.firstName());
        user.setLastName(form.lastName());
        user.setLogin(form.login());
        user.setPassword(form.password());
        userRepository.save(user);
    }

    @Override
    public void delete(long id, Authentication authentication) {
        User userConnected = userRepository.findByLogin(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("utilisateur non trouvé"));
        if (!userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("utilisateur non trouvé")).getLogin().equals(userConnected.getLogin())) throw new BadCredentialsException("acces non authorisé");
        userRepository.deleteById(id);
    }
}
