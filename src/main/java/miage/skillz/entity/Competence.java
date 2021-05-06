package miage.skillz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "competence")
@ToString(exclude = { "listBadges"})
public class Competence {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        // Id de la compétence pere
        private long idPere;

        // Nom de la compétence
        private String nom_competence;

        //Badges de la competence
        @Builder.Default
        @OneToMany( cascade = CascadeType.ALL, mappedBy="competence")
        @JsonIgnore
        Set<Badge> listBadges = new HashSet<>();

        // Mapping hibernate
        @Builder.Default
        @JsonIgnore
        @ManyToMany(mappedBy = "questionCompetences",fetch = FetchType.LAZY)
        Set<Question> listQuestions = new HashSet<>();

        @Builder.Default
        @JsonIgnore
        @OneToMany( cascade = CascadeType.ALL, mappedBy = "quizCompetence")
        Set<Quiz> listQuiz = new HashSet<>();

        @Builder.Default
        @JsonIgnore
        @OneToMany( cascade = CascadeType.ALL, mappedBy = "competence")
        Set<Poste> listPostes = new HashSet<>();

    // Constructeurs
    public Competence(long id) {
        this.id = id;
    }

    public Competence(String nom_competence) {
        this.nom_competence = nom_competence;
    }

    public Competence(String nom_competence, Long IdPere) {
        this.nom_competence = nom_competence;
        this.idPere = IdPere;
    }

    public Competence(long id, long IdPere, String nom_competence) {
        this.id = id;
        this.idPere = IdPere;
        this.nom_competence = nom_competence;
    }


    public Set<Badge> getListBadges() {
        return listBadges;
    }

    public void setListBadges(Set<Badge> listBadges) {
        this.listBadges = listBadges;
    }
}
