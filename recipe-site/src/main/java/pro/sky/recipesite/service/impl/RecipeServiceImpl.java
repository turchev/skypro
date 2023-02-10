package pro.sky.recipesite.service.impl;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pro.sky.recipesite.model.Recipe;
import pro.sky.recipesite.service.RepositoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RepositoryService<Recipe> {
    private static final Map<Long, Recipe> recipeRepository = new HashMap<>();

    @Override
    @Nullable
    public Recipe save(@Valid Recipe recipe) {
        return recipeRepository.put(recipe.getId(), recipe);
    }

    @Override
    public Optional<Recipe> findById(@Valid long primaryKey) {
        return Optional.ofNullable(recipeRepository.get(primaryKey));
    }
}
