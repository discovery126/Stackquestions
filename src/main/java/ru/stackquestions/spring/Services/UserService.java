package ru.stackquestions.spring.Services;

import ru.stackquestions.spring.Controller.payload.NewUserPayload;
import ru.stackquestions.spring.Controller.payload.UpdateUserPayload;
import ru.stackquestions.spring.Models.MyUser;

import java.util.List;
import java.util.Optional;

public interface UserService {

    MyUser createUser(NewUserPayload payload);
    public MyUser updateUser(UpdateUserPayload updatedUser, Integer id);
    void deleteUser(MyUser myUser);
    void deleteUserById(Integer userId);

    List<MyUser> getAllUser();

    Optional<MyUser> getUserById(Integer userId);
    Optional<MyUser> getUserByEmail(String email);
}
