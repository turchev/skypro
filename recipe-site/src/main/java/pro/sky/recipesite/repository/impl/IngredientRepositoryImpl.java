package pro.sky.recipesite.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import pro.sky.recipesite.configuration.FilesProperties;
import pro.sky.recipesite.exeption.EntityDuplicateException;
import pro.sky.recipesite.exeption.EntityNotFoundException;
import pro.sky.recipesite.model.Ingredient;
import pro.sky.recipesite.repository.IngredientRepository;
import pro.sky.recipesite.service.FileService;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Validated
@Repository
@RequiredArgsConstructor
public class IngredientRepositoryImpl implements IngredientRepository {
    private static long currentId;

    private final Map<Long, Ingredient> ingredientStorage = new HashMap<>();
    private final FilesProperties filesProperties;
    private final FileService fileService;

    private Path ingredientFilePath;

    @PostConstruct
    private void init() {
        ingredientFilePath = Path.of(filesProperties.filesDir(), filesProperties.ingredientFileName());
        ingredientStorage.putAll(fileService.readFromFile(ingredientFilePath, new TypeReference<>() {
        }));
    }

    @Override
    @Nullable
    public Ingredient save(@Valid Ingredient ingredient) {
        if (ingredientStorage.containsValue(ingredient)) {
            throw new EntityDuplicateException("Такой ингредиент уже имеется и не будет добавлен в хранилище");
        }
        Ingredient prevIngredient = ingredientStorage.put(++currentId, ingredient);
        saveIngredientsToFile();
        return prevIngredient;
    }

    @Override
    public Optional<Ingredient> findById(long id) {
        return Optional.ofNullable(ingredientStorage.get(id));
    }

    @Override
    @Nullable
    public Ingredient update(long id, Ingredient ingredient) {
        Ingredient updatedIngredient = ingredientStorage.replace(id, ingredient);
        if (updatedIngredient == null) {
            throw new EntityNotFoundException("Не найден ингредиент с id: " + id);
        }
        saveIngredientsToFile();
        return updatedIngredient;
    }

    @Override
    @Nullable
    public Ingredient delete(long id) {
        Ingredient deletedIngredient = ingredientStorage.remove(id);
        if (deletedIngredient == null) {
            throw new EntityNotFoundException("Не найден ингредиент с id: " + id);
        }
        saveIngredientsToFile();
        return deletedIngredient;
    }

    @Override
    public Map<Long, Ingredient> findAll() {
        if (ingredientStorage.isEmpty()) {
            throw new EntityNotFoundException("В хранилище нет ингредиентов");
        }
        return ingredientStorage;
    }

    private void saveIngredientsToFile() {
        fileService.saveToFile(ingredientStorage, ingredientFilePath);
    }
}
