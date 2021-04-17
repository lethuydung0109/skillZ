package miage.skillz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="reponsequestion")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReponseQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReponse;
    private String libelle;
    private Boolean isCorrect;  // true si c'est une bonne reponse
    private Boolean isSelected; // true si le user a sélectionné cette réponse

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="idQuestion")
    private Question question;

    public ReponseQuestion(String libelle, Boolean isCorrect, Boolean isSelected, Question question) {
        this.libelle = libelle;
        this.isCorrect = isCorrect;
        this.isSelected = isSelected;
        this.question = question;
    }
}
