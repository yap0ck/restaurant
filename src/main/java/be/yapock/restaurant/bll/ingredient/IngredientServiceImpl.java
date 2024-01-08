package be.yapock.restaurant.bll.ingredient;

import be.yapock.restaurant.dal.models.Ingredient;
import be.yapock.restaurant.dal.repositories.IngredientRepository;
import be.yapock.restaurant.pl.models.ingredient.IngredientForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService{
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void create(IngredientForm form) {
        Ingredient ingredient = Ingredient.builder()
                .ingredientName(form.ingredientName())
                .unit(form.unit())
                .build();
        ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getOne(String id) {
        return null;
    }

    @Override
    public Page<Ingredient> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(String id, IngredientForm form) {

    }

    @Override
    public void delete(String id) {

    }
}
