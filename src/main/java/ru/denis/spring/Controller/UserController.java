package ru.denis.spring.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.denis.spring.Controller.payload.NewUserPayload;
import ru.denis.spring.Controller.payload.UpdateUserPayload;
import ru.denis.spring.Exception.NoUserExistsException;
import ru.denis.spring.Models.MyUser;
import ru.denis.spring.Repositories.UserRepository;
import ru.denis.spring.Services.UserServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;
    protected static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<MyUser> getUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Integer userId) {
        Optional<MyUser> user = userService.getUserById(userId);

        if (user.isEmpty()) {
            throw new NoUserExistsException("this user is not found");
        } else {
            return ResponseEntity.ok(user.get());
        }
    }

    @PostMapping("/newUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody NewUserPayload newUser,
                                     BindingResult bindingResult,
                                     UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
                throw new BindException(bindingResult);
        } else {

            MyUser myUser = userService.createUser(newUser);
            LOGGER.debug("Received request to create the {}", newUser);
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/user/{userId}")
                            .build(Map.of("userId", myUser.getUserId())))
                    .body(myUser);
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@Valid @RequestBody UpdateUserPayload updateUserPayload,
                                     @PathVariable("id") Integer id,
                                     BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
                throw new BindException(bindingResult);
        } else {

            MyUser myUser = userService.updateUser(updateUserPayload,id);
            LOGGER.debug("Received request to patch the {}", updateUserPayload);
            return ResponseEntity.ok(myUser);
        }

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Integer userId) {
        userService.deleteUserById(userId);
    }
}