package pro.sky.recipesite.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@ToString
@Getter
@Setter
public class Ingredient extends AbstractEntity {
    private static long currentId;

    @PositiveOrZero
    private float weight;
    @NotBlank
    private String measureUnit;

    private Ingredient(String name) {
        super(++currentId, name);
    }

    public Ingredient(@NotBlank String name, @PositiveOrZero float weight, @NotBlank String measureUnit) {
        this(name);
        this.weight = weight;
        this.measureUnit = measureUnit;
    }
}
