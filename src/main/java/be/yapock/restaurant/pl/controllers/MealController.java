package be.yapock.restaurant.pl.controllers;

import be.yapock.restaurant.bll.meal.MealService;
import be.yapock.restaurant.pl.models.meal.MealForm;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meal")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public void create(MealForm form){
        mealService.create(form);
    }
}
