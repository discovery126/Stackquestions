package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Setter
@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

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

    public User(String email, String password, String nameUser, Date date_registration) {
        this.email = email;
        this.password = password;
        this.nameUser = nameUser;
        this.date_registration = date_registration;
    }
}
