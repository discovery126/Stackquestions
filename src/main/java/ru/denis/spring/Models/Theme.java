package ru.denis.spring.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@EqualsAndHashCode
@Entity(name = "theme")
public class Theme {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id")
    private Integer themeId;

    @Column(name = "theme_name")
    private String themeName;

    public Theme() {}

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    //    @ManyToMany
//    @JoinTable(name = "question_theme",
//            joinColumns = @JoinColumn(name = "theme_id"),
//            inverseJoinColumns = @JoinColumn(name = "question_id"))
//    private List<Question> questionList;

}
