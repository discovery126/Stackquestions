package ru.denis.spring.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "question")
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "question_header")
    private String questionHeader;

    @Column(name = "question_body")
    private String questionBody;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private MyUser owner;

    @ManyToMany
    @JoinTable(name = "question_theme",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    List<Theme> themeList;

    @ManyToMany
    @JoinTable(name = "question_answer",
            joinColumns = @JoinColumn(name = "question_id",referencedColumnName = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "answer_id"))
    private List<Answer> answerList;

}
