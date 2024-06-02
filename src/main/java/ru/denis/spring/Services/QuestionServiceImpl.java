package ru.denis.spring.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denis.spring.Controller.payload.NewQuestionPayload;
import ru.denis.spring.Controller.payload.UpdateQuestionPayload;
import ru.denis.spring.Controller.payload.UpdateUserPayload;
import ru.denis.spring.Exception.BadThemeQuestionsException;
import ru.denis.spring.Exception.NoQuestionExistsException;
import ru.denis.spring.Exception.NoUserExistsException;
import ru.denis.spring.Exception.QuestionAlreadyExistsException;
import ru.denis.spring.Models.MyUser;
import ru.denis.spring.Models.Question;
import ru.denis.spring.Models.Theme;
import ru.denis.spring.Repositories.QuestionRepository;
import ru.denis.spring.Repositories.ThemeRepository;
import ru.denis.spring.Repositories.UserRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    final private QuestionRepository questionRepository;
    final private UserRepository userRepository;
    final private ThemeRepository themeRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> getQuestion(Integer id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question createQuestion(NewQuestionPayload payload, String email) {
        Optional<MyUser> myUser = userRepository.findByEmail(email);
        Optional<Question> question = questionRepository.findByQuestionHeader(payload.questionHeader());
        if (myUser.isEmpty())
            throw new NoUserExistsException("this user is not found");
        else if (question.isPresent()) {
            throw new QuestionAlreadyExistsException("there is already a question with the same header");
        }
        else {
            List<Theme> themeList = checkCorrectThemes(payload.theme());
            // JPA сам сохранит themeList в таблицу question_theme, потому что всё происходит в транзакции
            Question questionResult = new Question(payload.questionHeader(),
                    payload.questionBody(),
                    myUser.get(),
                    themeList);
            return questionRepository.save(questionResult);

//            questionRepository.createQuestion(payload.questionHeader(), payload.questionBody(), myUser.get().getUserId());
//            themeList.forEach(th -> questionRepository.createThemeQuestions(questionResult.getQuestionId(),th.getThemeId()));
//            final Question newQuestion = questionRepository.findByQuestionHeader(payload.questionHeader()).get();
        }
    }

    @Override
    @Transactional
    public Question updateUser(UpdateQuestionPayload updateQuestionPayload, Integer id) {
        Optional<Question> updatedQuestion = questionRepository.findById(id);
        if (updatedQuestion.isEmpty())
            throw new NoQuestionExistsException("this questions not found");

        if (updateQuestionPayload.questionHeader()!=null) {
            updatedQuestion.get().setQuestionHeader(updateQuestionPayload.questionHeader());
        }
        if (updateQuestionPayload.questionBody()!=null) {
            updatedQuestion.get().setQuestionHeader(updateQuestionPayload.questionBody());
        }
        if (updateQuestionPayload.theme()!=null) {
            updatedQuestion.get()
                    .setThemeList(checkCorrectThemes(updateQuestionPayload.theme()));
        }
        return updatedQuestion.get();
    }

    @Override
    public List<Theme> checkCorrectThemes(List<String> themeString) {
        List<Theme> themeList = new ArrayList<>();
        for (String theme : themeString) {

            Optional<Theme> themeOptional = themeRepository.findByThemeName(theme);

            if (themeOptional.isEmpty())
                throw new BadThemeQuestionsException("Theme, when you write not found");

            themeList.add(themeOptional.get());
        }
        return themeList;
    }

    @Override
    @Transactional
    public void deleteQuestionById(Integer questionId) {
        questionRepository.deleteByQuestionId(questionId);
    }
}
