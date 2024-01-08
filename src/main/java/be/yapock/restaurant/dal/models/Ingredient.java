package be.yapock.restaurant.dal.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class Ingredient {
    @Id
    @Column(length = 50)
    private String ingredientName;
    @Column(length = 50)
    private String unit;
}
