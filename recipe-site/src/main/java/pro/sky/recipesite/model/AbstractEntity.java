package pro.sky.recipesite.model;

import lombok.Getter;
import org.apache.commons.lang3.Validate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Getter
public abstract class AbstractEntity {

    @Positive
    protected final long id;

    @NotBlank
    protected String name;

    protected AbstractEntity(Long id, String name) {
        setName(name);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity that)) return false;
        return getId() == that.getId() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public void setName(String name) {
        Validate.notBlank(name, "Название обязательный параметр");
        this.name = name;
    }
}
