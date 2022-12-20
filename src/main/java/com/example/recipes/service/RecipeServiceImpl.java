package com.example.recipes.service;

import com.example.recipes.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Map<Long, Recipe> recipes = new TreeMap<>();
    private static long lastId = 0;


    @Override
    public void addRecipe(Recipe recipe) {
        recipes.put(lastId++, recipe);
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
            return newRecipe;
        }
        return null;
    }
    @Override
    public boolean deleteRecipe(long id){
        if (recipes.containsKey(id)) {
            recipes.remove(id);
            return true;
        }
        return false;
    }

}
