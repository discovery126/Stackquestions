package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Entity(name = "question")
@Setter
@Getter
@NoArgsConstructor
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qusetion_id")
    private int questionId;

    @Column(name = "question_header")
    private String questionHeader;

    @Column(name = "question_body")
    private String questionBody;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    private User owner;

    @ManyToMany(mappedBy = "questionList")
    List<Theme> themeList;
}
