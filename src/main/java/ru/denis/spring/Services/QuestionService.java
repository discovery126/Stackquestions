package ru.denis.spring.Services;

import ru.denis.spring.Controller.payload.NewQuestionPayload;
import ru.denis.spring.Controller.payload.UpdateQuestionPayload;
import ru.denis.spring.Controller.payload.UpdateUserPayload;
import ru.denis.spring.Models.MyUser;
import ru.denis.spring.Models.Question;
import ru.denis.spring.Models.Theme;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    Question createQuestion(NewQuestionPayload payload, String email);
    Question updateUser(UpdateQuestionPayload updateQuestionPayload, Integer id);
    List<Question> getAllQuestions();
    Optional<Question> getQuestion(Integer id);
    void deleteQuestionById(Integer questionId);

    List<Theme> checkCorrectThemes(List<String> themeString);
}
