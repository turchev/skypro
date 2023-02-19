package pro.sky.recipesite.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import pro.sky.recipesite.configuration.FilesProperties;
import pro.sky.recipesite.exeption.EntityDuplicateException;
import pro.sky.recipesite.exeption.EntityNotFoundException;
import pro.sky.recipesite.model.Ingredient;
import pro.sky.recipesite.model.Recipe;
import pro.sky.recipesite.repository.IngredientRepository;
import pro.sky.recipesite.repository.RecipeRepository;
import pro.sky.recipesite.service.FileService;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Repository
@RequiredArgsConstructor
public class RecipeRepositoryImpl implements RecipeRepository {
    private static long currentId;
    private final IngredientRepository ingredientRepository;
    private final Map<Long, Recipe> recipeStorage = new HashMap<>();
    private final FilesProperties filesProperties;
    private final FileService fileService;

    private Path recipeFilePath;

    @PostConstruct
    private void init() {
        recipeFilePath = Path.of(filesProperties.filesDir(), filesProperties.recipeFileName());
        recipeStorage.putAll(fileService.readFromFile(recipeFilePath, new TypeReference<>() {
        }));
    }

    @Override
    @Nullable
    public Recipe save(Recipe recipe) {
        if (recipeStorage.containsValue(recipe)) {
            throw new EntityDuplicateException("Такой рецепт уже имеется и не будет добавлен в хранилище");
        }
        recipe.getIngredients().forEach(ingredient -> {
            try {
                ingredientRepository.save(ingredient);
            } catch (EntityDuplicateException e) {
                log.debug(e.getMessage());
            }
        });
        Recipe prevRecipe = recipeStorage.put(++currentId, recipe);
        saveRecipiesToFile();
        return prevRecipe;
    }

    @Override
    public Optional<Recipe> findById(long id) {
        return Optional.ofNullable(recipeStorage.get(id));
    }

    @Override
    @Nullable
    public Recipe update(long id, Recipe recipe) {
        Recipe updatedRecipe = recipeStorage.replace(id, recipe);
        if (updatedRecipe == null) {
            throw new EntityNotFoundException("Не найден рецепт с id: " + id);
        }
        saveRecipiesToFile();
        return updatedRecipe;
    }

    @Override
    @Nullable
    public Recipe delete(long id) {
        Recipe deletedRecipe = recipeStorage.remove(id);
        if (deletedRecipe == null) {
            throw new EntityNotFoundException("Не найден рецепт с id: " + id);
        }
        saveRecipiesToFile();
        return deletedRecipe;
    }

    @Override
    public Map<Long, Recipe> findAll() {
        if (recipeStorage.isEmpty()) {
            throw new EntityNotFoundException("В хранилище нет рецептов");
        }
        return recipeStorage;
    }

    @Override
    public Map<Long, Recipe> findByIngredientId(Long... ingredientId) {
        Set<Ingredient> ingredients = new HashSet<>(ingredientId.length);
        for (Long id : ingredientId) {
            Optional<Ingredient> foundById = ingredientRepository.findById(id);
            foundById.ifPresent(ingredients::add);
        }
        Map<Long, Recipe> foundRecipes = recipeStorage.entrySet().stream()
                .filter(entry -> entry.getValue().getIngredients()
                        .stream()
                        .anyMatch(ingredients::contains))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (foundRecipes.isEmpty()) {
            throw new EntityNotFoundException("Не найдены рецепты по ингредиентам с id: "
                    + Arrays.toString(ingredientId));
        }
        return foundRecipes;
    }

    private void saveRecipiesToFile() {
        fileService.saveToFile(recipeStorage, recipeFilePath);
    }
}
