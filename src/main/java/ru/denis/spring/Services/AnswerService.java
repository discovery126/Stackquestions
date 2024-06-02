package ru.denis.spring.Services;

import ru.denis.spring.Controller.payload.NewAnswerPayload;
import ru.denis.spring.Controller.payload.UpdateAnswerPayload;
import ru.denis.spring.Models.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

    List<Answer> getAllAnswerById(Integer questionId);
    List<Answer> getAllAnswers();
    Answer createAnswer(NewAnswerPayload newAnswerPayload,Integer questionId,String email);
    Answer updateAnswer(UpdateAnswerPayload updateAnswerPayload, Integer answerId,String email);
    void deleteById(Integer answerId,String email);
}
