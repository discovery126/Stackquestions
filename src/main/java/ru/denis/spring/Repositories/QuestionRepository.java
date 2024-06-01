package ru.denis.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.denis.spring.Models.Question;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    Optional<Question> findByQuestionHeader(String questionHeader);

    @Modifying
    void deleteByQuestionId(int userId);

    @Modifying
    @Transactional
    @Query(value = "insert into question(question_header,question_body,user_id) values(:QUESTIONHEADER,:QUESTIONBODY,:USERID);",nativeQuery = true)
    void createQuestion(@Param("QUESTIONHEADER") String questionHeader, @Param("QUESTIONBODY") String questionBody, @Param("USERID") Integer userId);

    @Modifying
    @Transactional
    @Query(value = "insert into question_theme(question_id,theme_id) values(:QUESTIONID,:THEMEID);",nativeQuery = true)
    void createThemeQuestions(@Param("QUESTIONID") Integer questionId, @Param("THEMEID") Integer themeId);

}
