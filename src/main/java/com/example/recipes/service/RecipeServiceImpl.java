package com.example.recipes.service;

import com.example.recipes.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final FilesService filesService;

    private static Map<Long, Recipe> recipes = new TreeMap<>();
    private static long lastId = 0;

    public RecipeServiceImpl( FilesService filesService) {
        this.filesService = filesService;

    }
    @PostConstruct
    private void unit(){
        readFromFile();
    }

    @Override
    public void addRecipe(Recipe recipe) {
        recipes.put(lastId++, recipe);
        saveToFile();

    }

    @Override
    public Recipe getRecipe(long id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        }
        return null;
    }


    @Override
    public Recipe editRecipe(long id, Recipe newRecipe) {
        if (recipes.containsKey(id)) {
            recipes.put(id, newRecipe);
            saveToFile();
            return newRecipe;
        }

        return null;
    }

    @Override
    public boolean deleteRecipe(long id) {
        if (recipes.containsKey(id)) {
            recipes.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }
    @Override
    public File createRecipesTxtFile() throws FileNotFoundException {
        Path path = filesService.createTempFile("Recipes");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (Recipe recipe : recipes.values()) {
                writer.append(recipe.toString());
                writer.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path.toFile();
    }

    private void readFromFile() {
        try {
            String json = filesService.readRecipesFromFile();
            if(!json.isBlank()){
                recipes = new ObjectMapper().readValue(json, new TypeReference<>() {
                });
                lastId = recipes.size();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            filesService.saveRecipesToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
