package be.yapock.restaurant.bll.recipe;

import be.yapock.restaurant.pl.models.recipe.RecipeForm;
import org.springframework.security.core.Authentication;

public interface RecipeService {
    void create(RecipeForm form, Authentication authentication);
}
