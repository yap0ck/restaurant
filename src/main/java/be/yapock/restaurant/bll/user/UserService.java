package be.yapock.restaurant.bll.user;

import be.yapock.restaurant.pl.models.user.AuthDTO;
import be.yapock.restaurant.pl.models.user.LoginForm;
import be.yapock.restaurant.pl.models.user.UserDTO;
import be.yapock.restaurant.pl.models.user.UserForm;

import java.util.List;

public interface UserService {
    void register(UserForm form);
    AuthDTO login(LoginForm form);
    UserDTO getOne(long id);
    List<UserDTO> getAll();
}
