package com.example.recipes.controllers;

import com.example.recipes.model.Ingredient;
import com.example.recipes.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ingredient")
@RestController
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping()
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(ingredient);
    }


    @GetMapping("/get")
    public ResponseEntity<Ingredient> getIngredient(@RequestParam int ingredientId) {
        Ingredient ingredient = ingredientService.getIngredient(ingredientId);
        if (ingredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable long id, @RequestBody Ingredient newIngredient) {
        if (ingredientService.getIngredient(id) != null) {
            Ingredient ingredient = ingredientService.editIngredient(id, newIngredient);
            return ResponseEntity.ok(ingredient);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable long id) {
        if (ingredientService.deleteIngredient(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
