package pro.sky.recipesite.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesite.model.Recipe;
import pro.sky.recipesite.repository.RecipeRepository;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeRepository recipeRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable("id") long recipeId) {
        return ResponseEntity.of(recipeRepository.findById(recipeId));
    }

    @PostMapping
    public ResponseEntity<Recipe> save(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeRepository.save(recipe));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeRepository.update(recipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> delete(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeRepository.delete(recipe));
    }

    /**
     *  Тут вывод всех рецептов и два доп задания:
     *  1. Поиск рецептов по id ингредиента;
     *  2. Поиск рецепта по нескольким ингредиентам.
     */
    @GetMapping
    public ResponseEntity<Map<Long, Recipe>> findByIngredientId(
            @RequestParam(name = "ingredientId", required = false) Long... ingredientId) {
        if (ingredientId == null) {
            return ResponseEntity.ok(recipeRepository.findAll());
        }
        return ResponseEntity.ok(recipeRepository.findByIngredientId(ingredientId));
    }
}
