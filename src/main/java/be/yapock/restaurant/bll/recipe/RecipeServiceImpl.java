package be.yapock.restaurant.bll.recipe;

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

    public RecipeServiceImpl(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void create(RecipeForm form, Authentication authentication) {
        User user = userRepository.findByLogin(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("utilisateur non trouvé"));
        Recipe recipe = Recipe.builder()
                .instructions(form.instructions())
                .user(user)
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
        recipeRepository.save(recipe);
    }
}
