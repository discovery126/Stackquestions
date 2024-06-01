package ru.denis.spring.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Component
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

    @ManyToMany
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id",referencedColumnName = "authority_id"))
    private List<Authority> authorities;
}
