package com.example.recipes.service;

import com.example.recipes.model.Ingredient;
import com.example.recipes.model.Recipe;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final FilesService filesService;
    private static Map<Long, Ingredient> ingredients = new TreeMap<>();
    private static long lastId = 0;

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }
    @PostConstruct
    private void unit(){
        readFromFile();
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredients.put(lastId++, ingredient);
        saveToFile();
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
            saveToFile();
            return ingredients.get(id);
        }
        return null;
    }
    @Override
    public boolean deleteIngredient(long id ){
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }
    private void readFromFile() {
        try {
            String json = filesService.readIngredientsFromFile();
            if(!json.isBlank()){
                ingredients = new ObjectMapper().readValue(json, new TypeReference<>() {
                });
                lastId = ingredients.size();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            filesService.saveIngredientToFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}
