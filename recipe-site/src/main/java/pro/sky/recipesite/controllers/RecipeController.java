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
import pro.sky.recipesite.model.Recipe;
import pro.sky.recipesite.repository.RecipeRepository;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name = "Рецепты", description = "CRUD-операции для рецептов и поиск рецептов по ингредиентам")
public class RecipeController {
    private final RecipeRepository recipeRepository;

    @Operation(summary = "Получение рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт найден"),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос")})
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable("id") @Positive long id) {
        return ResponseEntity.of(recipeRepository.findById(id));
    }

    @Operation(summary = "Создание рецепта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт сохранен"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос")})
    @PostMapping
    public ResponseEntity<Recipe> save(@RequestBody @Valid Recipe recipe) {
        try {
            return ResponseEntity.ok(recipeRepository.save(recipe));
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Operation(summary = "Изменение рецепта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт обновлен"),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос")})
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@PathVariable("id") @Positive long id,
                                         @RequestBody @Valid Recipe recipe) {
        try {
            return ResponseEntity.ok(recipeRepository.update(id, recipe));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(summary = "Удаление рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепт удален"),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> delete(@PathVariable("id") @Positive long id) {
        try {
            return ResponseEntity.ok(recipeRepository.delete(id));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Operation(
            summary = "Получение всех рецептов или получение(поиск) рецептов по ингредиентам",
            description = "1. Если параметр не передается, то возвращает все рецепты, " +
                    "\n2. Если передан в качестве параметра/ов id ингредиента/ов, то возвращается список рецептов, в которых они найдены")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Рецепты найдены"),
            @ApiResponse(responseCode = "404", description = "Рецепты не найдены"),
            @ApiResponse(responseCode = "400", description = "Не корректный запрос")})
    @GetMapping
    public ResponseEntity<Map<Long, Recipe>> findByIngredientId(
            @RequestParam(name = "ingredientId", required = false) Long... ingredientId) {
        try {
            if (ingredientId == null) {
                return ResponseEntity.ok(recipeRepository.findAll());
            }
            return ResponseEntity.ok(recipeRepository.findByIngredientId(ingredientId));
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
