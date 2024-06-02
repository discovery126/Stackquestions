package ru.denis.spring.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode
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

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private MyUser userId;

    public Answer() {}

    public Answer(String answerHeader, String answerBody, Integer questionId, MyUser userId) {
        this.answerHeader = answerHeader;
        this.answerBody = answerBody;
        this.questionId = questionId;
        this.userId = userId;
        this.date = new Date();
        this.favourite = false;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerHeader() {
        return answerHeader;
    }

    public void setAnswerHeader(String answerHeader) {
        this.answerHeader = answerHeader;
    }

    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MyUser getUserId() {
        return userId;
    }

    public void setUserId(MyUser userId) {
        this.userId = userId;
    }
}
