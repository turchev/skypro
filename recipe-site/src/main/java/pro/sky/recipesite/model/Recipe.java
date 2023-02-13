package pro.sky.recipesite.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Setter
@Getter
public class Recipe extends AbstractEntity {
    private static long currentId;

    @Positive
    private int cookingTime;
    @Positive
    private int portion = 1;
    @NotEmpty
    private List<Ingredient> ingredients;
    @NotEmpty
    private List<String> steps;

    private Recipe(String name) {
        super(++currentId, name);
    }

    public Recipe(String name, int cookingTime, int portion, List<Ingredient> ingredients, List<String> steps) {
        this(name);
        setCookingTime(cookingTime);
        setPortion(portion);
        setIngredients(ingredients);
        setSteps(steps);
    }

    public String toString() {
        return String.format("Рецепт (Название = %s, Время приготовления = %d мин., Количество порций = %d)",
                StringUtils.capitalize(name), this.getCookingTime(), this.getPortion());
    }
}
