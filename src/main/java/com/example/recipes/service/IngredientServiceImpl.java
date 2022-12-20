package com.example.recipes.service;

import com.example.recipes.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final Map<Long, Ingredient> ingredients = new TreeMap<>();
    private static long lastId = 0;

    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredients.put(lastId++, ingredient);
    }

    @Override
    public Ingredient getIngredient(long id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        }
        return null;
    }


    @Override
    public Ingredient editIngredient(long id, Ingredient newingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, newingredient);
            return newingredient;
        }
        return null;
    }
    @Override
    public boolean deleteIngredient(long id ){
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            return true;
        }
        return false;
    }
}
