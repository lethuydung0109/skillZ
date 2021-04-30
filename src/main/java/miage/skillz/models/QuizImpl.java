package miage.skillz.models;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    private String theme;
    private Long idNiveau;
    private Long seuilValidation;
    private long duree;
    private Long idCompetence;
    @Builder.Default
    private String dateOfCreation= new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    @Builder.Default
    private Set<Long> quizQuestionsId = new HashSet<>();

    public QuizImpl(String name, Long idNiveau, String theme, Long seuilValidation, long duree, Long idCompetence) {
        this.name = name;
        this.idNiveau = idNiveau;
        this.theme = theme;
        this.seuilValidation = seuilValidation;
        this.duree = duree;
        this.idCompetence = idCompetence;
    }
}
