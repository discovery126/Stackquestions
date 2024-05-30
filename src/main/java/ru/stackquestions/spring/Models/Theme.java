package ru.stackquestions.spring.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "theme")
public class Theme {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id")
    private Integer themeId;

    @Column(name = "theme_name")
    private String themeName;

    @ManyToMany
    @JoinTable(name = "question_theme",
            joinColumns = @JoinColumn(name = "theme_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questionList;

    public Theme(String themeName) {
        this.themeName = themeName;
    }
}
