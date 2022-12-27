package com.example.recipes.service;

public interface FilesService {
    boolean saveIngredientToFile(String json);

    String readIngredientsFromFile();

    boolean saveRecipesToFile(String json);

    String readRecipesFromFile();
}
