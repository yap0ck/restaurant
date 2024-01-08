package be.yapock.restaurant.bll.ingredient;

import be.yapock.restaurant.dal.models.Ingredient;
import be.yapock.restaurant.pl.models.ingredient.IngredientForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IngredientService {
    void create(IngredientForm form);
    Ingredient getOne(String id);
    Page<Ingredient> getAll(Pageable pageable);
    void update(String id, IngredientForm form);
    void delete(String id);
}
