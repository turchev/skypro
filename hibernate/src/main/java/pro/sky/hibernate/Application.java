package pro.sky.hibernate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.hibernate.dao.RoleDao;
import pro.sky.hibernate.dao.UserDao;
import pro.sky.hibernate.entity.Role;
import pro.sky.hibernate.entity.User;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Application {
    public static final String APPLICATION_PREFIX = "app";

    private static final String ADMIN_ROLE_NAME = "Администратор";
    private static final String ADVANCED_USER_ROLE_NAME = "Опытный пользователь";
    private static final String USER_ROLE_NAME = "Пользователь";
    private static final long USER_ID = 1L;

    private final UserDao userDao;
    private final RoleDao roleDao;

    @PostConstruct
    private void init() {

        // Создание роли
        roleDao.create(new Role(ADMIN_ROLE_NAME));

        // Создание нового пользователя без ролей
        userDao.create(new User("Ленин В.И.", "LeninVI", "passwdLVI"));

        // Создание нового пользователя одновременно с созданием и присвоением ему ролей
        Set<Role> roles = Set.of(
                new Role(ADVANCED_USER_ROLE_NAME),
                new Role(USER_ROLE_NAME));
        userDao.create(new User("Сталин И.В.", "StalinVI", "passwdSIV", roles));

        // Получение списка пользователей из БД (без ролей)
        userDao.findAll().forEach(System.out::println);

        // Получение конкретного пользователя по id с ролями
        User foundUser = userDao.findByIdWithRoles(USER_ID);
        System.out.println("Пользователь: " + foundUser + " роли: " + foundUser.getRoles());

        // Получение списка ролей пользователя по имени роли
        System.out.println(ADVANCED_USER_ROLE_NAME + ": " + roleDao.getUsersByRoleName(ADVANCED_USER_ROLE_NAME));

        // Изменение пользователя
        foundUser.setName("Хрущев Н.С.");
        userDao.update(foundUser);

        // Удаление пользователя
        userDao.delete(foundUser);
    }
}