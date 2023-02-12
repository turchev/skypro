package pro.sky.recipesite.repository.impl;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import pro.sky.recipesite.model.Ingredient;
import pro.sky.recipesite.repository.IngredientRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class IngredientRepositoryImpl implements IngredientRepository {
    private final Map<Long, Ingredient> ingredientRepository = new HashMap<>();

    @Override
    @Nullable
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.put(ingredient.getId(), ingredient);
    }

    @Override
    public Optional<Ingredient> findById(long primaryKey) {
        return Optional.ofNullable(ingredientRepository.get(primaryKey));
    }

    @Override
    @Nullable
    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.replace(ingredient.getId(), ingredient);
    }

    @Override
    @Nullable
    public Ingredient delete(Ingredient ingredient) {
        return ingredientRepository.remove(ingredient.getId());
    }

    @Override
    public Map<Long, Ingredient> findAll() {
        return ingredientRepository;
    }
}
