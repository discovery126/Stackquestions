package ru.denis.spring.Repositories;

import ru.denis.spring.Models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {

}
