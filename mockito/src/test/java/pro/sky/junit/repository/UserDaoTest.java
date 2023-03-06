package pro.sky.junit.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserDaoTest {

    private UserDao userDao;

    @BeforeEach
    void setUp() {
        userDao = new UserDaoImpl();
    }

    @Test
    @Description("Тестирование с именем пользователя, существующего в списке")
    void testGetUserByNameFromList() {
        Correct.USER_NAME_LIST.forEach(userName -> assertNotNull(userDao.getUserByName(userName)));
    }

    @Test
    @Description("Тестирование с именем пользователя, не существующего в списке")
    void testGetUserByNameNotFromList() {
        NotCorrect.USER_NAME_LIST.forEach(userName -> assertNull(userDao.getUserByName(userName)));
    }

    private static class Correct {
        static final List<String> USER_NAME_LIST = List.of(
                "Тургенев И.С.", "Барто А.Л.", "Ахматова А.А.", "Гумилёв Н.C.");
    }

    private static class NotCorrect {
        static final List<String> USER_NAME_LIST = List.of(
                "Гагарин Ю.А.", "Титов Г.С.", "Елисеев А.С.");
    }
}