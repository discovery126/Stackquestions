package ru.stackquestions.spring.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.stackquestions.spring.Controller.payload.NewUserPayload;
import ru.stackquestions.spring.Controller.payload.UpdateUserPayload;
import ru.stackquestions.spring.Models.MyUser;
import ru.stackquestions.spring.Services.UserServiceImpl;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final MessageSource messageSource;

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<MyUser> getUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Integer userId,
                                     Locale locale) {
        Optional<MyUser> user = userService.getUserById(userId);

        if (user.isEmpty()) {
            String message = this.messageSource.getMessage("error.400.title", new Object[0],"error.400.title",
                    locale);

            ProblemDetail problemDetail = ProblemDetail
                    .forStatusAndDetail(HttpStatus.BAD_REQUEST,message);

            problemDetail.setProperty("errors","Такого юзера не существует");

            return ResponseEntity.badRequest()
                    .body(problemDetail);

        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("/newUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody NewUserPayload newUser,
                                     BindingResult bindingResult,
                                     UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            MyUser myUser = userService.createUser(newUser);
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
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            MyUser myUser = userService.updateUser(updateUserPayload,id);
            return ResponseEntity.ok(myUser);
        }

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Integer userId) {
        userService.deleteUserById(userId);
    }
}