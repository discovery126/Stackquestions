package ru.denis.spring.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.denis.spring.Controller.payload.NewAnswerPayload;
import ru.denis.spring.Controller.payload.UpdateAnswerPayload;
import ru.denis.spring.Controller.payload.UpdateQuestionPayload;
import ru.denis.spring.Exception.*;
import ru.denis.spring.Models.Answer;
import ru.denis.spring.Models.MyUser;
import ru.denis.spring.Models.Question;
import ru.denis.spring.Repositories.AnswerRepository;
import ru.denis.spring.Repositories.QuestionRepository;
import ru.denis.spring.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }
    @Override
    public List<Answer> getAllAnswerById(Integer questionId) {
        List<Answer> answerList = answerRepository.getAnswerList(questionId);
        if (answerList==null)
            throw new NoQuestionExistsException("this question not found");
        return answerList;
    }

    @Override
    @Transactional
    public Answer createAnswer(NewAnswerPayload newAnswerPayload,Integer questionId,String email) {
        Optional<MyUser> myUser = userRepository.findByEmail(email);
        Optional<Answer> answer = answerRepository.findByAnswerHeader(newAnswerPayload.answerHeader());
        Optional<Question> question = questionRepository.findById(questionId);
        if (myUser.isEmpty())
            throw new NoUserExistsException("this user is not found");
        else if (answer.isPresent()) {
            throw new AnswerAlreadyExistsException("there is already a answer with the same header");
        } else if (question.isEmpty()) {
            throw new NoQuestionExistsException("this question not found");
        } else {
            Answer answerResult = answerRepository.save(
                    new Answer(newAnswerPayload.answerHeader(),
                    newAnswerPayload.answerBody(),
                    questionId,
                    myUser.get()));
            // Сохраняет таблицу question_answer потому что мы в транзакции
            question.get().getAnswerList().add(answerResult);

            return answerResult;
        }
    }
    @Override
    @Transactional
    public Answer updateAnswer(UpdateAnswerPayload updateAnswerPayload, Integer answerId,String email) {
        Optional<Answer> updatedAnswer = answerRepository.findById(answerId);
        Optional<MyUser> myUser = userRepository.findByEmail(email);
        if (updatedAnswer.isEmpty())
            throw new NoAnswerExistsException("this answer is not found");
        else if (myUser.isEmpty())
            throw new NoUserExistsException("this user is not found");
        else if (!myUser.get().getUserId().equals(updatedAnswer.get().getUserId().getUserId()))
            throw new NoUserExistsException("you can't change this answer");

        if (updateAnswerPayload.answerHeader()!=null) {
            updatedAnswer.get().setAnswerHeader(updateAnswerPayload.answerHeader());
        }
        if (updateAnswerPayload.answerBody()!=null) {
            updatedAnswer.get().setAnswerBody(updateAnswerPayload.answerBody());
        }
        return updatedAnswer.get();
    }

    @Override
    public void deleteById(Integer answerId,String email) {
        Optional<MyUser> myUser = userRepository.findByEmail(email);
        Optional<Answer> answer = answerRepository.findById(answerId);
        if (answer.isEmpty())
            throw new NoAnswerExistsException("this answer is not found");
        else if (myUser.isEmpty())
            throw new NoUserExistsException("this user is not found");
        else if (!myUser.get().getUserId().equals(answer.get().getUserId().getUserId()))
            throw new NoUserExistsException("you can't change this answer");

        answerRepository.deleteById(answerId);
    }



}
