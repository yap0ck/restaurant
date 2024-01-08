package be.yapock.restaurant.pl.controllers;

import be.yapock.restaurant.bll.recipe.RecipeService;
import be.yapock.restaurant.pl.models.recipe.RecipeForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public void create(@RequestBody RecipeForm form, Authentication authentication){
        recipeService.create(form,authentication);
    }
}
