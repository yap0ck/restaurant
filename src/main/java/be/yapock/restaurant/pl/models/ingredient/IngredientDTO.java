package be.yapock.restaurant.pl.models.ingredient;

import be.yapock.restaurant.dal.models.Ingredient;

public record IngredientDTO(
        String ingredientName,
        String unit) {
    public static IngredientDTO fromEntity(Ingredient ingredient){
        return new IngredientDTO(ingredient.getIngredientName(), ingredient.getUnit());
    }
}
