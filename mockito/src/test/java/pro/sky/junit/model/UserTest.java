package pro.sky.junit.model;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Это вне рамок задания. Тут пробовал проверку валидации
 */
class UserTest {

    private static final Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @Description("Проверка валидации имени пользователя")
    public void testUserNameValidators() {
        List<User> incorrectUsers = List.of(
                new User(NotCorrect.USER_NAME_NULL, Correct.EMAIL),
                new User(NotCorrect.USER_NAME_EMPTY, Correct.EMAIL),
                new User(NotCorrect.USER_NAME_SPACE, Correct.EMAIL),
                new User(NotCorrect.USER_NAME_TAB, Correct.EMAIL));
        for (User user : incorrectUsers) {
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            assertTrue(violations.size() > 0);
            violations.forEach(ucv -> {
                assertEquals("name", ucv.getPropertyPath().toString());
                assertEquals("Имя не должно быть пустым", ucv.getMessageTemplate());
            });
        }
    }

    @Test
    @Description("Проверка валидации адреса электронной почты пользователя")
    public void testUserEmailValidators() {
        List<User> incorrectUsers = List.of(
                new User(Correct.USER_NAME, NotCorrect.EMAIL_1),
                new User(Correct.USER_NAME, NotCorrect.EMAIL_2));
        for (User user : incorrectUsers) {
            Set<ConstraintViolation<User>> violations = validator.validate(user);
            assertTrue(violations.size() > 0);
            violations.forEach(ucv -> {
                assertEquals("email", ucv.getPropertyPath().toString());
                assertEquals("Email должен быть корректным адресом электронной почты", ucv.getMessageTemplate());
            });
        }
    }

    private static class Correct {
        static final String USER_NAME = "turchev";
        static final String EMAIL = "turchev@gmail.com";
    }

    private static class NotCorrect {
        static final String USER_NAME_EMPTY = "";
        static final String USER_NAME_SPACE = " ";
        static final String USER_NAME_TAB = "\n";
        static final String USER_NAME_NULL = null;

        static final String EMAIL_1 = "turchevgmail.com";
        static final String EMAIL_2 = "turchev@gmailcom";
    }
}