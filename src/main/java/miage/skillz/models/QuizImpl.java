package miage.skillz.models;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuizImpl {

    private Long idQuiz;
    private String name;
    private String niveau;
    private String theme;
    private Long pourcentageValidation;
    private long duree;
    @Builder.Default
    private Set<Long> quizQuestions = new HashSet<>();
    @Builder.Default
    private Set<Long> quizCompetences = new HashSet<>();
    @Builder.Default
    private Set<Long> users = new HashSet<>();

    public QuizImpl(String name, String niveau, String theme, Long pourcentageValidation, long duree) {
        this.name = name;
        this.niveau = niveau;
        this.theme = theme;
        this.pourcentageValidation = pourcentageValidation;
        this.duree = duree;
    }
}
