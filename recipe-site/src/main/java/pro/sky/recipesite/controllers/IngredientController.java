package pro.sky.recipesite.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesite.model.Ingredient;
import pro.sky.recipesite.service.RepositoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final RepositoryService<Ingredient> repositoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> totalShipped(@PathVariable("id") long ingredientId) {
        return ResponseEntity.of(repositoryService.findById(ingredientId));
    }

    @PostMapping
    public ResponseEntity<Ingredient> save(@Valid @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(repositoryService.save(ingredient));
    }
}
