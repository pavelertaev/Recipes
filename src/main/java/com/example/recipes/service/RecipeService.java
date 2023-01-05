package com.example.recipes.service;

import com.example.recipes.model.Recipe;

import java.io.File;
import java.io.FileNotFoundException;

public interface RecipeService {
    public void addRecipe(Recipe recipe);
    public Recipe getRecipe(long id);

    Recipe editRecipe(long id, Recipe newRecipe);

    boolean deleteRecipe(long id);

    File createRecipesTxtFile() throws FileNotFoundException;
}
