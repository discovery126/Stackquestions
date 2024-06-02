package ru.denis.spring.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denis.spring.Controller.payload.NewUserPayload;
import ru.denis.spring.Controller.payload.UpdateUserPayload;
import ru.denis.spring.Exception.NoUserExistsException;
import ru.denis.spring.Exception.UserAlreadyExistsException;
import ru.denis.spring.Models.MyUser;
import ru.denis.spring.Repositories.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public MyUser createUser(NewUserPayload payload) {
        if (getUserByEmail(payload.email()).isPresent())
            throw new UserAlreadyExistsException("error");
            //Желательно не сообщать пользователю что этот пользователь уже зарегистрирован
            //throw new UserAlreadyExistsException("this user already registered");
        MyUser myUser = new MyUser(payload.email(),
                passwordEncoder.encode(payload.password()),
                payload.nameUser());
        return userRepository.save(myUser);
    }
    @Override
    @Transactional
    public MyUser updateUser(UpdateUserPayload updatedUser, Integer id) {
        Optional<MyUser> myUser = getUserById(id);

        if (myUser.isEmpty())
            throw new NoUserExistsException("This user is not found");

        if (updatedUser.nameUser()!=null) {
            myUser.get().setNameUser(updatedUser.nameUser());
        }
        if (updatedUser.email()!=null) {
            myUser.get().setEmail(updatedUser.email());
        }
        if (updatedUser.password()!=null) {
            myUser.get().setPassword(updatedUser.password());
        }

        return myUser.get();
    }

    @Override
    @Transactional
    public void deleteUserById(Integer userId) {
        userRepository.deleteByUserId(userId);
    }
    @Override
    public List<MyUser> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<MyUser> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }
    @Override
    public Optional<MyUser> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
