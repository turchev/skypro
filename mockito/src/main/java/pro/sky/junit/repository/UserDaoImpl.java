package pro.sky.junit.repository;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import pro.sky.junit.model.User;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDao {

    private final static List<User> users = new ArrayList<>();

    public UserDaoImpl() {
        users.addAll(List.of(
                new User("Толстой Л.Н.", "testMail@mail.com"),
                new User("Тургенев И.С.", "testMail@mail.com"),
                new User("Барто А.Л.", "testMail@mail.com"),
                new User("Ахматова А.А.", "testMail@mail.com"),
                new User("Гумилёв Н.C.", "testMail@mail.com"))
        );
    }

    @Override
    @Nullable
    public User getUserByName(@NotNull String name) {
        return users.stream()
                .filter(Objects::nonNull)
                .filter(u -> name.equals(u.getName()))
                .findFirst().orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(users);
    }
}
