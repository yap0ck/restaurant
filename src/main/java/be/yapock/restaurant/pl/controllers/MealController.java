package be.yapock.restaurant.pl.controllers;

import be.yapock.restaurant.bll.meal.MealService;
import be.yapock.restaurant.pl.models.meal.MealDto;
import be.yapock.restaurant.pl.models.meal.MealForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/meal")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public void create(@RequestBody MealForm form){
        mealService.create(form);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealDto> getOne(@PathVariable String id){
        return ResponseEntity.ok(MealDto.fromEntity(mealService.getOne(id)));
    }
}
