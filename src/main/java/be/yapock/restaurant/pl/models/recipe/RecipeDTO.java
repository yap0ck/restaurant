package be.yapock.restaurant.pl.models.recipe;

import java.util.List;

public record RecipeDTO(
        String instructions,
        long userId) {
}
