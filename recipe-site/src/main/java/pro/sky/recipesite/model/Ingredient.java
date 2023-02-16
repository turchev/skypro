package pro.sky.recipesite.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Ingredient extends AbstractEntity {

    @PositiveOrZero
    @Setter
    private float weight;
    @NotBlank
    private String measureUnit;

    public Ingredient(String name, float weight, String measureUnit) {
        setName(name);
        setWeight(weight);
        setMeasureUnit(measureUnit);
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String toString() {
        return String.format("Игредиент (Название = %s, Количество = %.2f, Единица измерения = %s)",
                StringUtils.capitalize(name), this.getWeight(), StringUtils.lowerCase(this.getMeasureUnit()));
    }
}
