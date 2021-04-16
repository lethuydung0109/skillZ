package miage.skillz.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "competence")
/*Data
@Builder
@AllArgsConstructor
@NoArgsConstructor*/
public class Competence {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        // Id de la compétence pere
        private long id_pere;

        // Nom de la compétence
        private String nom_competence;

//        //Badges de la competence
        @OneToMany( cascade = CascadeType.ALL, mappedBy="competence")
        @JsonIgnore
        Set<Badge> listBadges = new HashSet<Badge>();


        // Mapping hibernate
        @JsonIgnore
        @ManyToMany(mappedBy = "questionCompetences",fetch = FetchType.LAZY)
        Set<Question> listQuestions = new HashSet<>();

        @JsonIgnore
        @ManyToMany(mappedBy = "quizCompetences",fetch = FetchType.LAZY)
        Set<Quiz> listQuiz = new HashSet<>();


    // Constructeurs     
    public Competence() {}

    public Competence(long id) {
        this.id = id;
    }

    public Competence(String nom_competence) {
        this.nom_competence = nom_competence;
    }
    public Competence(String nom_competence, Long IdPere) {
        this.nom_competence = nom_competence;
        this.id_pere = IdPere;
    }


    public Competence(long id, long id_père, String nom_competence) {
        this.id = id;
        this.id_pere = id_père;
        this.nom_competence = nom_competence;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_pere() {
        return id_pere;
    }

    public void setId_pere(long id_pere) {
        this.id_pere = id_pere;
    }

    public String getNom_competence() {
        return nom_competence;
    }

    public void setNom_competence(String nom_competence) {
        this.nom_competence = nom_competence;
    }

    public Set<Badge> getListBadges() {
        return listBadges;
    }

    public void setListBadges(Set<Badge> listBadges) {
        this.listBadges = listBadges;
    }

    public Set<Quiz> getListQuiz() {
        return listQuiz;
    }

    public void setListQuiz(Set<Quiz> listQuiz) {
        this.listQuiz = listQuiz;
    }

    public Set<Question> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(Set<Question> listQuestions) {
        this.listQuestions = listQuestions;
    }
}
