package pro.sky.junit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import pro.sky.junit.model.User;
import pro.sky.junit.repository.UserDao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserDao userDao;

    private UserService userService;

    /**
     * Формируем возвращаемое значение из списка пользователей в методе-заглушке findAllUsers() и
     * инициализируем userService, путем внедрения объекта-заглушки userDao
     */
    @BeforeEach
    void setUp() {
        Mockito.when(userDao.findAllUsers()).thenReturn(Correct.USER_LIST);
        userService = new UserServiceImpl(userDao);
    }

    @Test
    @Description("Тестируем метод checkUserExist на поиск имеющихся пользователей")
    void testCheckUserExist() {
        Correct.USER_LIST.forEach(user -> assertTrue(userService.checkUserExist(user)));
    }

    @Test
    @Description("Тестируем метод checkUserExist на поиск отсутствующих пользователей")
    void testCheckUserNotExist() {
        NotCorrect.USER_LIST.forEach(user -> assertFalse(userService.checkUserExist(user)));
    }

    private static class Correct {
        static final List<User> USER_LIST = List.of(
                new User("Тургенев И.С.", "testMail@mail.com"),
                new User("Барто А.Л.", "testMail@mail.com"),
                new User("Ахматова А.А.", "testMail@mail.com"),
                new User("Гумилёв Н.C.", "testMail@mail.com"));
    }

    private static class NotCorrect {
        static final List<User> USER_LIST = List.of(
                new User("Гагарин Ю.А.", "testMail@mail.com"),
                new User("Титов Г.С.", "testMail@mail.com"),
                new User("Елисеев А.С.", "testMail@mail.com"));
    }
}