package com.example.recipes.controllers;

import com.example.recipes.model.Recipe;
import com.example.recipes.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/recipe")
@RestController
public class RecipeController {


    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping("get")
    public ResponseEntity<Recipe> getRecipe(@RequestParam int recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @PutMapping("put")
    public ResponseEntity<Recipe> editRecipe(@RequestBody Recipe recipe){
        Recipe recipe = recipeService.editRecipe(Recipe recipe );
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
}
