package pro.sky.recipesite.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipesite.model.Ingredient;
import pro.sky.recipesite.repository.IngredientRepository;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable("id") long ingredientId) {
        return ResponseEntity.of(ingredientRepository.findById(ingredientId));
    }

    @PostMapping
    public ResponseEntity<Ingredient> save(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientRepository.save(ingredient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientRepository.update(ingredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete(@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientRepository.delete(ingredient));
    }

    @GetMapping
    public ResponseEntity<Map<Long, Ingredient>> findAll() {
        return ResponseEntity.ok(ingredientRepository.findAll());
    }
}
