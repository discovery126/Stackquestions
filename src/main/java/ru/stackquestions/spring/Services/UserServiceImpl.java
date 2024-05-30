package ru.stackquestions.spring.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stackquestions.spring.Controller.payload.NewUserPayload;
import ru.stackquestions.spring.Controller.payload.UpdateUserPayload;
import ru.stackquestions.spring.Exception.NoUserExistsException;
import ru.stackquestions.spring.Models.MyUser;
import ru.stackquestions.spring.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public MyUser createUser(NewUserPayload payload) {
        userRepository.createUser(payload.email(), payload.nameUser(), payload.password());
        //Не проверяю isPresent, потому что после создания обьекта, он точно будет
        return getUserByEmail(payload.email()).get();
    }
    @Override
    @Transactional
    public MyUser updateUser(UpdateUserPayload updatedUser, Integer id) {
        Optional<MyUser> myUser = getUserById(id);
        if (myUser.isPresent()) {
            if (updatedUser.nameUser()!=null) {
                myUser.get().setNameUser(updatedUser.nameUser());
            }
            if (updatedUser.email()!=null) {
                myUser.get().setEmail(updatedUser.email());
            }
            if (updatedUser.password()!=null) {
                myUser.get().setPassword(updatedUser.password());
            }
            userRepository.save(myUser.get());
            return myUser.get();
        }
        throw new NoUserExistsException("This user is not found");
    }

    @Override
    public void deleteUser(MyUser myUser) {

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
