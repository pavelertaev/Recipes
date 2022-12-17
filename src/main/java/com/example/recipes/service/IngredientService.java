package com.example.recipes.service;

import com.example.recipes.model.Ingredient;

public interface IngredientService {
    public void addIngredient(Ingredient ingredient);
    public Ingredient getIngredient(long id);

}
