package miage.skillz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="quizz")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@CheckQuizz
public class Quiz {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuizz;
    private String name;
    /*@Enumerated(EnumType.STRING)
    @Column(length = 20)*/
    private String niveau;
    private String theme;
    private Long pourcentageValidation;
    private String duree;
    //private Set<String> competences;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "question_quizz",
            joinColumns = @JoinColumn(name = "idQuizz"),
            inverseJoinColumns = @JoinColumn(name = "idQuestion"))
    @Builder.Default
    private Set<Question> questionsQuizz = new HashSet<>();

    public Quiz(String name, String niveau, String theme, Long pourcentageValidation, String duree, Set<Question> questionsQuizz) {
        this.name = name;
        this.niveau = niveau;
        this.theme = theme;
        this.pourcentageValidation = pourcentageValidation;
        this.duree = duree;
        this.questionsQuizz = questionsQuizz;
    }
}
