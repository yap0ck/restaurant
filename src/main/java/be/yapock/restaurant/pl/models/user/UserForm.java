package be.yapock.restaurant.pl.models.user;

public record UserForm(
        String lastName,
        String firstName,
        String login,
        String password
) {
}
