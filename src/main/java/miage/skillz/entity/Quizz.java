package miage.skillz.entity;

import lombok.*;
import miage.skillz.enumeration.Niveau;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="quizz")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@CheckQuizz
public class Quizz {

    private static final long serialVersionUID = -2054386655979281969L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuizz;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Niveau niveau;
    private String theme;
    private Long pourcentageValidation;
    private LocalTime duree;
    //private Set<String> competences;
    @ManyToMany
    @JoinTable(
            name = "question_quizz",
            joinColumns = @JoinColumn(name = "idQuizz"),
            inverseJoinColumns = @JoinColumn(name = "idQuestion"))
    @Builder.Default
    private Set<Question> questionsQuizz = new HashSet<>();

}
