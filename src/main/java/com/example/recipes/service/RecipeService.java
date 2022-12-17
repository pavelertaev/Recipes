package com.example.recipes.service;

import com.example.recipes.model.Recipe;

public interface RecipeService {
    public void addRecipe(Recipe recipe);
    public Recipe getRecipe(long id);

}
