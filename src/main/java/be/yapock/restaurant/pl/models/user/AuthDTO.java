package be.yapock.restaurant.pl.models.user;

public record AuthDTO(
        String login,
        String token
) {
}
