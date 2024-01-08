package be.yapock.restaurant.pl.controllers;

import be.yapock.restaurant.bll.meal.MealService;
import be.yapock.restaurant.pl.models.meal.MealDto;
import be.yapock.restaurant.pl.models.meal.MealForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseEntity<Page<MealDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(mealService.getAll(pageable).map(MealDto::fromEntity));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{id}")
    public void update(String id, MealForm form){
        mealService.update(id,form);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("{id}")
    public void delete(String id){
        mealService.delete(id);
    }
}
