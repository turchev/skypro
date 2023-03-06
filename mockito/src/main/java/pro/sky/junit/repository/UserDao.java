package pro.sky.junit.repository;

import org.springframework.lang.Nullable;
import pro.sky.junit.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserDao {

    @Nullable
    User getUserByName(@NotNull String name);

    List<User> findAllUsers();
}
