package Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity(name = "theme")
@Getter
@Setter
@NoArgsConstructor
public class Theme {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id")
    int themeId;

    @Column(name = "theme_name")
    String themeName;

    @ManyToMany
    @JoinTable(name = "question_theme",
            joinColumns = @JoinColumn(name = "theme_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    private List<Question> questionList;

    public Theme(String themeName) {
        this.themeName = themeName;
    }
}
