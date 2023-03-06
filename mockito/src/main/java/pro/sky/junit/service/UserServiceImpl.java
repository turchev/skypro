package pro.sky.junit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.junit.model.User;
import pro.sky.junit.repository.UserDao;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public boolean checkUserExist(@NotNull User user) {
        return userDao.findAllUsers().contains(user);
    }
}
