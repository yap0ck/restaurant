package be.yapock.restaurant.bll.meal;

import be.yapock.restaurant.dal.models.Meal;
import be.yapock.restaurant.pl.models.meal.MealForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MealService {
    void create(MealForm form);
    Meal getOne(String mealname);
    Page<Meal> getAll(Pageable pageable);
    void update(String mealName, MealForm form);
    void delete(String mealName);
}
