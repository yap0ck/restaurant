package be.yapock.restaurant.pl.controllers;

import be.yapock.restaurant.bll.ingredient.IngredientService;
import be.yapock.restaurant.pl.models.ingredient.IngredientDTO;
import be.yapock.restaurant.pl.models.ingredient.IngredientForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public void create(@RequestBody IngredientForm form){
        ingredientService.create(form);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> getOne(@PathVariable String id){
        return ResponseEntity.ok(IngredientDTO.fromEntity(ingredientService.getOne(id)));
    }
}
