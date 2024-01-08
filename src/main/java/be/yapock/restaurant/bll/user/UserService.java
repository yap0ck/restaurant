package be.yapock.restaurant.bll.user;

import be.yapock.restaurant.dal.models.User;
import be.yapock.restaurant.pl.models.user.AuthDTO;
import be.yapock.restaurant.pl.models.user.LoginForm;
import be.yapock.restaurant.pl.models.user.UserDTO;
import be.yapock.restaurant.pl.models.user.UserForm;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    void register(UserForm form);
    AuthDTO login(LoginForm form);
    User getOne(long id);
    List<User> getAll();
    void update(UserForm form, long id, Authentication authentication);
    void delete(long id, Authentication authentication);
}
