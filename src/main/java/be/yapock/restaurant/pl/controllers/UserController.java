package be.yapock.restaurant.pl.controllers;

import be.yapock.restaurant.bll.user.UserService;
import be.yapock.restaurant.pl.models.user.UserForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping
    public void register(@RequestBody UserForm form){
        userService.register(form);
    }
}
