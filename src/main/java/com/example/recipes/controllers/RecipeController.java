package com.example.recipes.controllers;

import com.example.recipes.model.Recipe;
import com.example.recipes.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/recipe")
@RestController
@Tag(name = "Рецепты", description = "CRUD- операции и другие эндпоинты для работы с рецептами")
public class RecipeController {


    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(description = "Добавление рецепта ")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт был добавлен" ,
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Recipe.class)
                            )

                    }


            )
    })
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    @Operation(description = "Поиск рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт был найден" ,
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Recipe.class)
                            )

            }


            )
    })
    public ResponseEntity<Recipe> getRecipe(@RequestParam int recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{id}")
    @Operation(description = "Изменение рецепта по id")
    public ResponseEntity<Recipe> editIngredient(@PathVariable long id, @RequestBody Recipe newRecipe) {
        if (recipeService.getRecipe(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Recipe recipe = recipeService.editRecipe(id, newRecipe);
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление рецепта по id")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long id) {
        if (recipeService.deleteRecipe(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
