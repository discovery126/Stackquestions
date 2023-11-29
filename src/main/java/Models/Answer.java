package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Entity(name = "answer")
@Getter
@Setter
@NoArgsConstructor
public class Answer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    int answerId;

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
    private User owner;

    public Answer(String answerHeader, String answerBody, Boolean favourite, Date date) {
        this.answerHeader = answerHeader;
        this.answerBody = answerBody;
        this.favourite = favourite;
        this.date = date;
    }
}
