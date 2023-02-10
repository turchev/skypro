package pro.sky.recipesite.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipesite.model.Ingredient;
import pro.sky.recipesite.service.RepositoryService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements RepositoryService<Ingredient> {
    private static final Map<Long, Ingredient> ingredientRepository = new HashMap<>();

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.put(ingredient.getId(), ingredient);
    }

    @Override
    public Optional<Ingredient> findById(long primaryKey) {
        return Optional.ofNullable(ingredientRepository.get(primaryKey));
    }
}
