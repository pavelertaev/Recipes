package com.example.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private String nameIngredient ;
    private int amountIngredient ;
    private String unit;
}
