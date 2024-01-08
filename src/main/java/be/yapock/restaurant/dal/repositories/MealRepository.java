package be.yapock.restaurant.dal.repositories;

import be.yapock.restaurant.dal.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, String> {
    Optional<Meal> findByMealName(String mealName);
}
