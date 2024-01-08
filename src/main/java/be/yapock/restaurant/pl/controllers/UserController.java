package be.yapock.restaurant.pl.controllers;

import be.yapock.restaurant.bll.user.UserService;
import be.yapock.restaurant.pl.models.user.AuthDTO;
import be.yapock.restaurant.pl.models.user.LoginForm;
import be.yapock.restaurant.pl.models.user.UserDTO;
import be.yapock.restaurant.pl.models.user.UserForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public AuthDTO login(@RequestBody LoginForm form){
        return userService.login(form);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public UserDTO getOne(@PathVariable long id){
        return userService.getOne(id);
    }
}
