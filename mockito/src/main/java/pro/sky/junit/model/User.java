package pro.sky.junit.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * Поля id и email не обязательные, но пусть будут :)
 */
@Getter
@EqualsAndHashCode
@ToString
public class User {

    private static Long genId = 0L;

    @Positive(message = "Должно быть положительное число")
    private final Long id;

    @Setter
    @NotBlank(message = "Имя не должно быть пустым")
    private String name;

    @Setter
    @Email(message = "Email должен быть корректным адресом электронной почты",
            regexp = "^\\S+@\\S+\\.\\S+$")
    private String email;

    public User(String name) {
        this.id = ++genId;
        setName(name);
    }

    public User(String name, String email) {
        this(name);
        setEmail(email);
    }
}
