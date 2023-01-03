package com.example.recipes.controllers;

import com.example.recipes.service.FilesService;
import com.example.recipes.service.RecipeService;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final FilesService filesService;

    public RecipeService recipeService;

    public FilesController(FilesService filesService, RecipeService recipeService) {
        this.filesService = filesService;
        this.recipeService = recipeService;
    }

    @GetMapping(value = "/export/ingredient" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> downloadIngredientFile() throws FileNotFoundException {
        File file = filesService.getFileIngredient();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment ; filename=\"Ingredient.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @GetMapping("/export/recipes/txt")
    public ResponseEntity<InputStreamResource> downloadRecipeTxtFile() throws IOException {

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(recipeService.createRecipesTxtFile().contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.txt\"")
                .body(recipeService.createRecipesTxtFile());

    }

    @GetMapping(value = "/export/recipe" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws FileNotFoundException {
        File file = filesService.getFileRecipe();

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment ; filename=\"Recipe.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import/ingredient" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        filesService.cleanIngredientFile();
        File ingredientFile = filesService.getFileIngredient();
        try(FileOutputStream fosIngredient = new FileOutputStream(ingredientFile)){
            IOUtils.copy(file.getInputStream(),fosIngredient);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    @PostMapping(value = "/import/recipe" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipeFile(@RequestParam MultipartFile file) {
        filesService.cleanRecipeFile();
        File recipeFile = filesService.getFileRecipe();
        try(FileOutputStream fosRecipe = new FileOutputStream(recipeFile)){
            IOUtils.copy(file.getInputStream(),fosRecipe);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



}
