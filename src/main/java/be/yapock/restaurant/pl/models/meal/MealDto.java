package be.yapock.restaurant.pl.models.meal;

import be.yapock.restaurant.dal.models.Meal;

public record MealDto(
        String mealName,
        String origine) {
    public static MealDto fromEntity(Meal meal){
        return new MealDto(meal.getMealName(), meal.getOrigine());
    }
}
