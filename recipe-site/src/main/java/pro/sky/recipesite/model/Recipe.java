package pro.sky.recipesite.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@ToString
@Getter
@Setter
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

    public Recipe(@NotBlank String name, @Positive int cookingTime, @Positive int portion) {
        this(name);
        this.cookingTime = cookingTime;
        this.portion = portion;
    }
}
