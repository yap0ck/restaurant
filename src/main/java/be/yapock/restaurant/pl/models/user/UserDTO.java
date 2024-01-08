package be.yapock.restaurant.pl.models.user;

import be.yapock.restaurant.dal.models.User;

public record UserDTO(
        String firstName,
        String lastName,
        String login) {
    public static UserDTO fromEntity(User user){
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getLogin());
    }
}
