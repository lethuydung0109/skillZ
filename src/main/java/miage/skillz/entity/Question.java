package miage.skillz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="question")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@CheckQuestion
public class Question {
    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuestion;
    private String libelle;
    private String theme;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idNiveau")
    private Niveau niveau;

    @Builder.Default
    @OneToMany(mappedBy="question",cascade = CascadeType.ALL)
    private Set<ReponseQuestion> reponsesQuestions = new HashSet<>();

    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "quizQuestions",fetch = FetchType.LAZY)
    Set<Quiz> listQuiz = new HashSet<>();

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable( name = "question_competences",
            joinColumns = @JoinColumn(name = "idQuestion"),
            inverseJoinColumns = @JoinColumn(name = "idCompetence"))
    private Set<Competence> questionCompetences = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

    public Question(String theme, String libelle,Niveau niveau) {
        this.theme = theme;
        this.libelle = libelle;
        this.niveau = niveau;
    }
}
