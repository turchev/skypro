package pro.sky.recipesite.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@EqualsAndHashCode
public abstract class AbstractEntity {

    @NotBlank
    protected String name;

    public void setName(String name) {
        this.name = name;
    }
}


