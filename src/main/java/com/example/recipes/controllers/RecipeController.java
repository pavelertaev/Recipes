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

    @GetMapping("/get")
    public ResponseEntity<Recipe> getRecipe(@RequestParam int recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editIngredient(@PathVariable long id, @RequestBody Recipe newRecipe) {
        if (recipeService.getRecipe(id) != null) {
            Recipe recipe = recipeService.editRecipe(id, newRecipe);
            return ResponseEntity.ok(recipe);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
