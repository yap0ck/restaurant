package be.yapock.restaurant.bll.recipe;

import be.yapock.restaurant.bll.meal.MealService;
import be.yapock.restaurant.dal.models.Recipe;
import be.yapock.restaurant.dal.models.User;
import be.yapock.restaurant.dal.repositories.RecipeRepository;
import be.yapock.restaurant.dal.repositories.UserRepository;
import be.yapock.restaurant.pl.models.recipe.RecipeForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService{
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final MealService mealService;

    public RecipeServiceImpl(RecipeRepository recipeRepository, UserRepository userRepository, MealService mealService) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.mealService = mealService;
    }

    @Override
    public void create(RecipeForm form, Authentication authentication) {
        User user = userRepository.findByLogin(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("utilisateur non trouvé"));
        Recipe recipe = Recipe.builder()
                .instructions(form.instructions())
                .user(user)
                .meal(mealService.getOne(form.mealName()))
                .build();
        recipeRepository.save(recipe);
    }

    @Override
    public Recipe getOne(long id) {
        return recipeRepository.findById(id).orElseThrow(()->new EntityNotFoundException("recette non trouvée"));
    }

    @Override
    public Page<Recipe> getAll(Pageable pageable) {
        return recipeRepository.findAll(pageable);
    }

    @Override
    public void update(long id, RecipeForm form, Authentication authentication) {
        User user = userRepository.findByLogin(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("utilisateur non trouvé"));
        Recipe recipe = getOne(id);
        if (!user.equals(recipe.getUser())) throw new BadCredentialsException("acces non authorizé");
        recipe.setInstructions(form.instructions());
        recipe.setMeal(mealService.getOne(form.mealName()));
        recipeRepository.save(recipe);
    }

    @Override
    public void delete(long id, Authentication authentication) {
        User user = userRepository.findByLogin(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("utilisateur non trouvé"));
        Recipe recipe = getOne(id);
        if (!user.equals(recipe.getUser())) throw new BadCredentialsException("acces non authorizé");
        recipeRepository.delete(recipe);
    }
}
