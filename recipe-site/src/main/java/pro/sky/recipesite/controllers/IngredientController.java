package pro.sky.recipesite.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.recipesite.exeption.EntityDuplicateException;
import pro.sky.recipesite.exeption.EntityNotFoundException;
import pro.sky.recipesite.model.Ingredient;
import pro.sky.recipesite.repository.IngredientRepository;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@Tag(name = "Ингредиенты для рецепта", description = "CRUD-операции для ингредиентов")
public class IngredientController {
    private final IngredientRepository ingredientRepository;

    @Operation(summary = "Получение ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент найден"),
            @ApiResponse(responseCode = "404", description = "Ингредиент не найден"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос")})
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable("id") @Positive long id) {
        return ResponseEntity.of(ingredientRepository.findById(id));
    }

    @Operation(summary = "Создание ингредиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент сохранен"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос")})
    @PostMapping
    public ResponseEntity<Ingredient> save(@RequestBody @Valid Ingredient ingredient) {
        try {
            return ResponseEntity.ok(ingredientRepository.save(ingredient));
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Изменение ингредиента")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент обновлен"),
            @ApiResponse(responseCode = "404", description = "Ингредиент не найден"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос")})
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> update(@PathVariable("id") @Positive long id,
                                             @RequestBody @Valid Ingredient ingredient) {
        try {
            return ResponseEntity.ok(ingredientRepository.update(id, ingredient));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Удаление ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент удален"),
            @ApiResponse(responseCode = "404", description = "Ингредиент не найден"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete(@PathVariable("id") @Positive long id) {
        try {
            return ResponseEntity.ok(ingredientRepository.delete(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Получение всех ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ингредиент найден"),
            @ApiResponse(responseCode = "404", description = "Ингредиент не найден"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос")})
    @GetMapping
    public ResponseEntity<Map<Long, Ingredient>> findAll() {
        try {
            return ResponseEntity.ok(ingredientRepository.findAll());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
