package be.yapock.restaurant.bll.user;

import be.yapock.restaurant.pl.models.user.UserForm;

public interface UserService {
    void register(UserForm form);
}
