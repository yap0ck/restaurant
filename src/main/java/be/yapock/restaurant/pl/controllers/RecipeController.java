package be.yapock.restaurant.pl.controllers;

import be.yapock.restaurant.bll.recipe.RecipeService;
import be.yapock.restaurant.pl.models.recipe.RecipeDTO;
import be.yapock.restaurant.pl.models.recipe.RecipeForm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok(RecipeDTO.fromEntity(recipeService.getOne(id)));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<Page<RecipeDTO>> getAll(Pageable pageable){
        return ResponseEntity.ok(recipeService.getAll(pageable).map(RecipeDTO::fromEntity));
    }
}
