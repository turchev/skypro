package pro.sky.recipesite.model;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
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
        setWeight(weight);
        setMeasureUnit(measureUnit);
    }

    public void setWeight(@PositiveOrZero float weight) {
        this.weight = weight;
    }

    public void setMeasureUnit(String measureUnit) {
        Validate.notBlank(measureUnit, "Единица измерения обязательный параметр");
        this.measureUnit = measureUnit;
    }

    public String toString() {
        return String.format("Игредиент (Название = %s, Количество = %.2f, Единица измерения = %s)",
                StringUtils.capitalize(name), this.getWeight(), StringUtils.lowerCase(this.getMeasureUnit()));
     }
}
