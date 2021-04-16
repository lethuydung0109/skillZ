package miage.skillz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="quiz")
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
    private Long idQuiz;
    private String name;
    /*@Enumerated(EnumType.STRING)
    @Column(length = 20)*/
    private String niveau;
    private String theme;
    private Long pourcentageValidation;
    private long duree;

    @JsonIgnore
    @Builder.Default
    @ManyToMany
    @JoinTable( name = "quiz_questions",
            joinColumns = @JoinColumn(name = "idQuiz"),
            inverseJoinColumns = @JoinColumn(name = "idQuestion"))
    private Set<Question> quizQuestions = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable( name = "quiz_competences",
            joinColumns = @JoinColumn(name = "idQuiz"),
            inverseJoinColumns = @JoinColumn(name = "idCompetence"))
    private Set<Competence> quizCompetences = new HashSet<>();

    public Quiz(String name, String niveau, String theme, Long pourcentageValidation, long duree) {
        this.name = name;
        this.niveau = niveau;
        this.theme = theme;
        this.pourcentageValidation = pourcentageValidation;
        this.duree = duree;
    }
}
