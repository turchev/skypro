package pro.sky.recipesite.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import pro.sky.recipesite.model.Ingredient;
import pro.sky.recipesite.model.Recipe;
import pro.sky.recipesite.repository.IngredientRepository;
import pro.sky.recipesite.repository.RecipeRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class RecipeRepositoryImpl implements RecipeRepository {
    private final Map<Long, Recipe> recipeRepository = new HashMap<>();
    private final IngredientRepository ingredientRepository;

    @Override
    @Nullable
    public Recipe save(Recipe recipe) {
        Recipe returnRecipe = recipeRepository.put(recipe.getId(), recipe);
        // добавил просто для тестирования, чтобы при создании рецепта его ингредиенты тоже создавались
        recipe.getIngredients().forEach(ingredientRepository::save);
        return returnRecipe;
    }

    @Override
    public Optional<Recipe> findById(long id) {
        return Optional.ofNullable(recipeRepository.get(id));
    }

    @Override
    @Nullable
    public Recipe update(Recipe recipe) {
        return recipeRepository.replace(recipe.getId(), recipe);
    }

    @Override
    @Nullable
    public Recipe delete(Recipe recipe) {
        return recipeRepository.remove(recipe.getId());
    }

    @Override
    public Map<Long, Recipe> findAll() {
        return recipeRepository;
    }

    @Override
    public Map<Long, Recipe> findByIngredientId(Long... ingredientId) {
        Set<Long> ingredientsId = Set.of(ingredientId);
        return recipeRepository.entrySet().stream()
                .filter(entry -> entry.getValue().getIngredients()
                        .stream()
                        .map(Ingredient::getId)
                        .anyMatch(ingredientsId::contains))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
