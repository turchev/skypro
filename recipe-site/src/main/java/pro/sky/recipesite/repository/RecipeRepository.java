package pro.sky.recipesite.repository;

import org.springframework.validation.annotation.Validated;
import pro.sky.recipesite.model.Recipe;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Validated
public interface RecipeRepository extends Repository<Recipe> {

    /**
     * Finds for recipes by ingredient id
     *
     * @param ingredientId Array of ingredient ID
     * @return all recipes from the repository by ingredient ID.
     */
    Map<Long, Recipe> findByIngredientId(@NotNull Long... ingredientId);
}
