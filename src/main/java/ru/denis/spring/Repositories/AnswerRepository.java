package ru.denis.spring.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.denis.spring.Models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.denis.spring.Models.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    Optional<Answer> findByAnswerHeader(String answerHeader);
    @Transactional
    @Query(value = "SELECT q.answerList from question q WHERE q.questionId=:questionId")
    List<Answer> getAnswerList(@Param("questionId") Integer questionId);


}