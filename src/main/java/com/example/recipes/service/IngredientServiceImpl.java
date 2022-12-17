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
        return ingredients.get(id);
    }
}
