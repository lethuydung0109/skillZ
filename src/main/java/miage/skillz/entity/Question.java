package miage.skillz.entity;

import lombok.*;
import miage.skillz.enumeration.Niveau;
import miage.skillz.validation.CheckQuestion;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name="question")
@Data
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
    private Long poids = 0L;
    private String niveau;
    //private Map<String,Boolean> reponsesQuestions;
    //private Set<String> competences;

    @ManyToMany(mappedBy = "questionsQuizz",fetch = FetchType.LAZY)
    Set<Quizz> listQuizz = new HashSet<>();
}
