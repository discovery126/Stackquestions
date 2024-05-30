package ru.stackquestions.spring.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "answer")
public class Answer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    Integer answerId;

    @Column(name = "answer_header")
    private String answerHeader;

    @Column(name = "answer_body")
    private String answerBody;

    @Column(name = "favourite")
    private Boolean favourite;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private MyUser owner;
}
