package ru.denis.spring.Repositories;

import ru.denis.spring.Models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<Theme,Integer> {

    Optional<Theme> findByThemeName(String themeName);
}
