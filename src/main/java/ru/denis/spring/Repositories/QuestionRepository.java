package ru.denis.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.denis.spring.Models.Answer;
import ru.denis.spring.Models.Question;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    Optional<Question> findByQuestionHeader(String questionHeader);

    @Modifying
    void deleteByQuestionId(int userId);
}
