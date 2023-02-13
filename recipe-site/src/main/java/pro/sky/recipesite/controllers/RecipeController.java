package pro.sky.recipesite.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesite.model.Recipe;
import pro.sky.recipesite.repository.RecipeRepository;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name = "Рецепты", description = "CRUD-операции для рецептов и поиск рецептов по ингредиентам")
public class RecipeController {
    private final RecipeRepository recipeRepository;

    @Operation(summary = "Получение рецепта по id")
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable("id") long recipeId) {
        return ResponseEntity.of(recipeRepository.findById(recipeId));
    }

    @Operation(summary = "Создание рецепта")
    @PostMapping
    public ResponseEntity<Recipe> save(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeRepository.save(recipe));
    }

    @Operation(summary = "Изменение рецепта")
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeRepository.update(recipe));
    }

    @Operation(summary = "Удаление рецепта по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> delete(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeRepository.delete(recipe));
    }

    @Operation(
            summary = "Получение всех рецептов или получение(поиск) рецептов по ингредиентам",
            description = "1. Если параметр не передается, то возвращает все рецепты, " +
                    "\n2. Если передан в качестве параметра/ов id ингредиента/ов, то возвращается список рецептов, в которых они найдены")
    @GetMapping
    public ResponseEntity<Map<Long, Recipe>> findByIngredientId(
            @RequestParam(name = "ingredientId", required = false) Long... ingredientId) {
        if (ingredientId == null) {
            return ResponseEntity.ok(recipeRepository.findAll());
        }
        return ResponseEntity.ok(recipeRepository.findByIngredientId(ingredientId));
    }
}
