package be.yapock.restaurant.bll.meal;

import be.yapock.restaurant.dal.models.Meal;
import be.yapock.restaurant.dal.repositories.MealRepository;
import be.yapock.restaurant.pl.models.meal.MealForm;
import org.springframework.stereotype.Service;

@Service
public class MealServiceImpl implements MealService{
    private final MealRepository mealRepository;

    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public void create(MealForm form) {
        Meal meal= Meal.builder()
                .mealName(form.mealName())
                .origine(form.mealOrigine())
                .build();
        mealRepository.save(meal);
    }
}
