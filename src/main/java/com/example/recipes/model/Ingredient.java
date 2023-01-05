package com.example.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String nameIngredient;
    private int amountIngredient;
    private String unit;

    @Override
    public String toString() {
        return nameIngredient + " - " + amountIngredient + " " + unit;
    }

}
