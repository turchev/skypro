package pro.sky.recipesite.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesite.model.Ingredient;
import pro.sky.recipesite.repository.IngredientRepository;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@Tag(name = "Ингредиенты для рецепта", description = "CRUD-операции для ингредиентов")
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    @Operation(summary = "Получение ингредиента по id")
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable("id") long ingredientId) {
        return ResponseEntity.of(ingredientRepository.findById(ingredientId));
    }

    @Operation(summary = "Создание ингредиента")
    @PostMapping
    public ResponseEntity<Ingredient> save(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientRepository.save(ingredient));
    }

    @Operation(summary = "Изменение ингредиента")
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientRepository.update(ingredient));
    }

    @Operation(summary = "Удаление ингредиента по id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientRepository.delete(ingredient));
    }

    @Operation(summary = "Получение всех ингредиентов")
    @GetMapping
    public ResponseEntity<Map<Long, Ingredient>> findAll() {
        return ResponseEntity.ok(ingredientRepository.findAll());
    }
}
