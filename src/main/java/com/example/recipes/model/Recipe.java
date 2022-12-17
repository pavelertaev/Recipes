package com.example.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Recipe {
    private String nameRecipe;
    private int timeForPreparing ;
    private List<Ingredient> ingredients;
    private List<String> steps;
}
