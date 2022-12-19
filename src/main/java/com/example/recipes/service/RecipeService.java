package com.example.recipes.service;

import com.example.recipes.model.Ingredient;
import com.example.recipes.model.Recipe;

public interface RecipeService {
    public void addRecipe(Recipe recipe);
    public Recipe getRecipe(long id);

    Recipe editRecipe(long id, Recipe recipe);

    boolean deleteRecipe(long id, Recipe recipe);
}
