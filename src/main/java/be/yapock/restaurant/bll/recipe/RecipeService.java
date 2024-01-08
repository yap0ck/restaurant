package be.yapock.restaurant.bll.recipe;

import be.yapock.restaurant.dal.models.Recipe;
import be.yapock.restaurant.pl.models.recipe.RecipeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface RecipeService {
    void create(RecipeForm form, Authentication authentication);
    Recipe getOne(long id);
    Page<Recipe> getAll(Pageable pageable);
}
