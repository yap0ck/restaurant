package be.yapock.restaurant.bll.user;

import be.yapock.restaurant.dal.models.User;
import be.yapock.restaurant.dal.repositories.UserRepository;
import be.yapock.restaurant.pl.config.security.JWTProvider;
import be.yapock.restaurant.pl.models.user.AuthDTO;
import be.yapock.restaurant.pl.models.user.LoginForm;
import be.yapock.restaurant.pl.models.user.UserDTO;
import be.yapock.restaurant.pl.models.user.UserForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(form.login(),form.password()));
        User user = userRepository.findByLogin(form.login()).orElseThrow(()->new UsernameNotFoundException("utilisateur pas trouvé"));
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthDTO(user.getLogin(), token);
    }

    @Override
    public UserDTO getOne(long id) {
        User user = userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("utilisateur pas trouvé"));
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getLogin());
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(UserDTO::fromEntity)
                .toList();
    }
}
