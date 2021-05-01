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
//@CheckQuiz
public class Quiz {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuiz;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idNiveau")
    private Niveau niveau;
    private String theme;
    private Long seuilValidation;
    private long duree;
    private String dateOfCreation;

    @JsonIgnore
    @Builder.Default
    @ManyToMany
    @JoinTable( name = "quiz_questions",
            joinColumns = @JoinColumn(name = "idQuiz"),
            inverseJoinColumns = @JoinColumn(name = "idQuestion"))
    private Set<Question> quizQuestions = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCompetence")
    @ToString.Exclude
    private Competence quizCompetence;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

//    @Builder.Default
//    @JsonIgnore
//    @ManyToMany(mappedBy = "myCreatedQuiz",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    private Set<User> users = new HashSet<>();

    public Quiz(String name, Niveau niveau, String theme, Long seuilValidation, long duree, String date, Competence competence) {
        this.name = name;
        this.niveau = niveau;
        this.theme = theme;
        this.seuilValidation = seuilValidation;
        this.duree = duree;
        this.dateOfCreation=date;
        this.quizCompetence=competence;
    }
}
