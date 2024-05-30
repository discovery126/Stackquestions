package ru.stackquestions.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stackquestions.spring.Models.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

}
