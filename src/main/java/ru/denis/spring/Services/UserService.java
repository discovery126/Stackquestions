package ru.denis.spring.Services;

import ru.denis.spring.Controller.payload.NewUserPayload;
import ru.denis.spring.Controller.payload.UpdateUserPayload;
import ru.denis.spring.Models.MyUser;

import java.util.List;
import java.util.Optional;

public interface UserService {

    MyUser createUser(NewUserPayload payload);
    MyUser updateUser(UpdateUserPayload updatedUser, Integer id);
    void deleteUserById(Integer userId);

    List<MyUser> getAllUser();

    Optional<MyUser> getUserById(Integer userId);
    Optional<MyUser> getUserByEmail(String email);
}
