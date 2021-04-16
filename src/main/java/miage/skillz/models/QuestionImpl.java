package miage.skillz.models;

import lombok.*;
import miage.skillz.entity.ReponseQuestion;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionImpl {

    private Long idQuestion;
    private String theme;
    private String libelle;
    @Builder.Default
    private long poids = 0;
    private String niveau;
    @Builder.Default
    private Set<Long> questionCompetences = new HashSet<>();
    @Builder.Default
    private Set<Long> listQuiz = new HashSet<>();
    @Builder.Default
    private Set<ReponseQuestion> reponsesQuestions = new HashSet<>();

}
