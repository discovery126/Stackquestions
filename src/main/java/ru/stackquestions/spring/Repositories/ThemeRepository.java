package ru.stackquestions.spring.Repositories;

import ru.stackquestions.spring.Models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Integer> {
}
