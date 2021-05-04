package miage.skillz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "badges")
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_competence")
    private Competence competence;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="niveauId")
    private Niveau niveau;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

    private String dateValidation;

    private long quizScore;

    public Badge(Competence competence, Niveau niveau, String dateValidation, long quizScore, User user) {
        this.competence = competence;
        this.niveau = niveau;
        this.dateValidation = dateValidation;
        this.quizScore = quizScore;
        this.user = user;
    }

    public Badge() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public String getDateValidation() {
        return dateValidation;
    }

    public void setDateValidation(String dateValiation) {
        this.dateValidation = dateValiation;
    }
}






