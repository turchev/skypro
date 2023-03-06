package pro.sky.junit.service;

import pro.sky.junit.model.User;

import javax.validation.constraints.NotNull;


public interface UserService {
    boolean checkUserExist(@NotNull User user);
}
