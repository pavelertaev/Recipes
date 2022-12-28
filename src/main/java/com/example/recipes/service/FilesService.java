package com.example.recipes.service;

import java.io.File;
import java.nio.file.Path;

public interface FilesService {
    boolean saveIngredientToFile(String json);

    String readIngredientsFromFile();

    boolean saveRecipesToFile(String json);

    String readRecipesFromFile();

    Path createTempFile(String suffix);

    void cleanFile(String fileName);

    File getFileIngredient();

    File getFileRecipe();
}
