package com.example.recipes.service;

import com.example.recipes.model.Ingredient;
import com.example.recipes.model.Recipe;
import org.springframework.core.io.InputStreamResource;

import java.io.FileNotFoundException;

public interface RecipeService {
    public void addRecipe(Recipe recipe);
    public Recipe getRecipe(long id);

    Recipe editRecipe(long id, Recipe newRecipe);

    boolean deleteRecipe(long id);

    InputStreamResource createRecipesTxtFile() throws FileNotFoundException;
}
