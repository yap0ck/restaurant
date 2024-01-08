package be.yapock.restaurant.bll.meal;

import be.yapock.restaurant.dal.models.Meal;
import be.yapock.restaurant.dal.repositories.MealRepository;
import be.yapock.restaurant.pl.models.meal.MealForm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Meal getOne(String mealname) {
        return mealRepository.findByMealName(mealname).orElseThrow(()->new EntityNotFoundException("plat non trouvé"));
    }

    @Override
    public Page<Meal> getAll(Pageable pageable) {
        return mealRepository.findAll(pageable);
    }

    @Override
    public void update(String mealName, MealForm form) {

    }

    @Override
    public void delete(String mealName) {

    }
}
