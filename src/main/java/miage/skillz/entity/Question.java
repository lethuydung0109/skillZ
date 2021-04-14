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
    private String name;
    @Builder.Default
    private long poids = 0;

    /*@Enumerated(EnumType.STRING)
    @Column(length = 20)*/
    private String niveau;
    //private Set<String> competences;
    @JsonIgnore
    @Builder.Default
    @ManyToMany(mappedBy = "questionsQuizz",fetch = FetchType.LAZY)
    Set<Quiz> listQuiz = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy="question")
    private Set<ReponseQuestion> reponsesQuestions = new HashSet<>();

    public Question(String name, long poids, String niveau, Set<Quiz> listQuiz, Set<ReponseQuestion> reponsesQuestions) {
        this.name = name;
        this.poids = poids;
        this.niveau = niveau;
        this.listQuiz = listQuiz;
        this.reponsesQuestions = reponsesQuestions;
    }
}
