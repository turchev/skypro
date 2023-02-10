package pro.sky.recipesite.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesite.model.Recipe;
import pro.sky.recipesite.service.RepositoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RepositoryService<Recipe> repositoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> totalShipped(@PathVariable("id") long recipeId) {
        return ResponseEntity.of(repositoryService.findById(recipeId));
    }

    @PostMapping
    public ResponseEntity<Recipe> save(@Valid @RequestBody Recipe recipe) {
        return ResponseEntity.ok(repositoryService.save(recipe));
    }
}
