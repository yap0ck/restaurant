package be.yapock.restaurant.pl.models.recipe;

import be.yapock.restaurant.dal.models.Recipe;

import java.util.List;

public record RecipeDTO(
        String instructions,
        long userId,
        String meal) {
    public static RecipeDTO fromEntity(Recipe recipe){
        return new RecipeDTO(recipe.getInstructions(),recipe.getUser().getId(),recipe.getMeal().getMealName());
    }
}
