package ru.denis.spring.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.denis.spring.Controller.payload.NewQuestionPayload;
import ru.denis.spring.Controller.payload.UpdateQuestionPayload;
import ru.denis.spring.Controller.payload.UpdateUserPayload;
import ru.denis.spring.Exception.NoQuestionExistsException;
import ru.denis.spring.Models.MyUser;
import ru.denis.spring.Models.Question;
import ru.denis.spring.Services.QuestionServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {


    private final QuestionServiceImpl questionService;
    protected static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);



    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Question> getAllQuestion() {
        return questionService.getAllQuestions();
    }

//    @GetMapping("/answer/{id}")
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public QuestionAnswer getQuestionAnswer(@PathVariable Integer id) {
//         Optional<QuestionAnswer> questionAnswer = questionService.getQuestionAnswer(id);
//        if (questionAnswer.isPresent())
//            return questionAnswer.get();
//        else throw new NoQuestionExistsException("Нет такого");
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Integer id) {
        Optional<Question> question = questionService.getQuestion(id);
        if (question.isEmpty()) {
            throw new NoQuestionExistsException("This question is not found");
        } else {
            return ResponseEntity.ok(question.get());
        }
    }

    @PostMapping("/newQuestion")
    public ResponseEntity<?> createQuestion(@RequestBody NewQuestionPayload newQuestionPayload,
                                            @AuthenticationPrincipal UserDetails userDetails,
                                            UriComponentsBuilder uriComponentsBuilder) {
        Question newQuestion = questionService.createQuestion(newQuestionPayload,userDetails.getUsername());
        return ResponseEntity
                .created(uriComponentsBuilder
                        .replacePath("/questions/{questionId}")
                        .build(Map.of("questionId", newQuestion.getQuestionId())))
                .body(newQuestion);

    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@Valid @RequestBody UpdateQuestionPayload updateQuestionPayload,
                                       @PathVariable("id") Integer id,
                                       BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        } else {
            Question question = questionService.updateUser(updateQuestionPayload,id);
            LOGGER.debug("Received request to patch the {}", updateQuestionPayload);
            return ResponseEntity.ok(question);
        }

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Integer userId) {
        questionService.deleteUserById(userId);
    }


}
