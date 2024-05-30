package ru.stackquestions.spring.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Component
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class MyUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String nameUser;

    @Column(name = "date_registration")
    private Date date_registration;

    @OneToMany(mappedBy = "owner")
    private List<Question> questionsList;

    @OneToMany(mappedBy = "owner")
    private List<Answer> answersList;

}
