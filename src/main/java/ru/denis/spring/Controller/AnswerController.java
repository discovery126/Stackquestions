package ru.denis.spring.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.denis.spring.Controller.payload.NewAnswerPayload;
import ru.denis.spring.Controller.payload.UpdateAnswerPayload;
import ru.denis.spring.Models.Answer;
import ru.denis.spring.Services.AnswerServiceImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerServiceImpl answerService;

    @GetMapping("/questions/{qustionId}/answers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAnswerQuestion(@PathVariable("qustionId") Integer questionId) {
        List<Answer> answerList = this.answerService.getAllAnswerById(questionId);
        return ResponseEntity.ok(answerList);
    }

    @PostMapping("/questions/{questionId}/answers/add")
    public ResponseEntity<?> sendAnswerToQuestion(@PathVariable("questionId") Integer questionId,
                                                  @AuthenticationPrincipal UserDetails userDetails,
                                                  @RequestBody NewAnswerPayload payload,
                                                  UriComponentsBuilder uriComponentsBuilder) {
        Answer newAnswer = answerService.createAnswer(payload,questionId,userDetails.getUsername());
        return ResponseEntity
                .created(uriComponentsBuilder
                        .replacePath("/answer/{answerId}")
                        .build(Map.of("answerId", newAnswer.getAnswerId())))
                .body(newAnswer);
    }

    @GetMapping("/answers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllAnswers() {
        return ResponseEntity.ok(answerService.getAllAnswers());
    }
    @PatchMapping("/answers/{answerId}")
    public ResponseEntity<?> updateAnswer(@PathVariable("answerId") Integer answerId,
                                                  @AuthenticationPrincipal UserDetails userDetails,
                                                  @RequestBody UpdateAnswerPayload updateAnswerPayload) {
        Answer updateAnswer = answerService.updateAnswer(updateAnswerPayload,answerId,userDetails.getUsername());
        return ResponseEntity.ok(updateAnswer);
    }
    @DeleteMapping("answers/{answerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnswer(@PathVariable("answerId") Integer answerId,
                             @AuthenticationPrincipal UserDetails userDetails) {
        answerService.deleteById(answerId,userDetails.getUsername());
    }


}
