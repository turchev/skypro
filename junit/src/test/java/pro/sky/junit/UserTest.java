package pro.sky.junit;

import org.junit.jupiter.api.Test;

import javax.validation.Valid;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static final String CORRECT_LOGIN = "turchev";
    private static final String CORRECT_EMAIL = "turchev@gmail.com";
    private static final String NOT_CORRECT_EMAIL1 = "turchevgmail.com";
    private static final String NOT_CORRECT_EMAIL2 = "turchev@gmailcom";

    @Test
    void testCreatingUserWithCorrectParametersInConstructor() {
        User user = new User(CORRECT_LOGIN, CORRECT_EMAIL);
        assertNotNull(user);
        assertEquals(user.getLogin(), CORRECT_LOGIN);
        assertEquals(user.getEmail(), CORRECT_EMAIL);
        assertEquals(user, new User(CORRECT_LOGIN, CORRECT_EMAIL));
    }

    @Test
    void testCreatingUserWithoutParametersInConstructor() {
        User user = new User();
        assertNotNull(user);
        user.setLogin(CORRECT_LOGIN);
        user.setEmail(CORRECT_EMAIL);
        assertEquals(user, new User(CORRECT_LOGIN, CORRECT_EMAIL));
    }

    @Test
    @Valid
    void testCreatingEmail() throws IllegalArgumentException {
        User user = new User();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> user.setEmail(NOT_CORRECT_EMAIL1));
        assertNotNull(thrown.getMessage());
        thrown = assertThrows(IllegalArgumentException.class, () -> user.setEmail(NOT_CORRECT_EMAIL2));
        assertNotNull(thrown.getMessage());
    }

    @Test
    void testLoginAndEmailEquality() throws IllegalArgumentException {
        User user = new User();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            user.setEmail(CORRECT_EMAIL);
            user.setLogin(CORRECT_EMAIL);
        });
        assertNotNull(thrown.getMessage());

        user.setEmail(null);
        user.setLogin(null);
        thrown = assertThrows(IllegalArgumentException.class, () -> {
            user.setLogin(CORRECT_EMAIL);
            user.setEmail(CORRECT_EMAIL);
        });
        assertNotNull(thrown.getMessage());

        assertNotEquals(user.getEmail(), user.getLogin());
    }
}