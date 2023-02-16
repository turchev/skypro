package pro.sky.recipesite.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import pro.sky.recipesite.exeption.EntityDuplicateException;
import pro.sky.recipesite.exeption.EntityNotFoundException;
import pro.sky.recipesite.model.Ingredient;
import pro.sky.recipesite.repository.IngredientRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Validated
@Repository
@RequiredArgsConstructor
public class IngredientRepositoryImpl implements IngredientRepository {
    private static long currentId;
    private final Map<Long, Ingredient> ingredientStorage = new HashMap<>();

    @Override
    @Nullable
    public Ingredient save(@Valid Ingredient ingredient) {
        if (ingredientStorage.containsValue(ingredient)) {
            throw new EntityDuplicateException("Такой ингредиент уже имеется и не будет добавлен в хранилище");
        }
        return ingredientStorage.put(++currentId, ingredient);
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
        return updatedIngredient;
    }

    @Override
    @Nullable
    public Ingredient delete(long id) {
        Ingredient deletedIngredient = ingredientStorage.remove(id);
        if (deletedIngredient == null) {
            throw new EntityNotFoundException("Не найден ингредиент с id: " + id);
        }
        return deletedIngredient;
    }

    @Override
    public Map<Long, Ingredient> findAll() {
        if (ingredientStorage.isEmpty()) {
            throw new EntityNotFoundException("В хранилище нет ингредиентов");
        }
        return ingredientStorage;
    }
}
