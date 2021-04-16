package miage.skillz.entity;
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
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        // Id de la compétence pere
        private long id_pere;

        // Nom de la compétence
        private String nom_competence;

//        //Badges de la competence
        @OneToMany( cascade = CascadeType.ALL, mappedBy="competence")
        Set<Badge> listBadges = new HashSet<Badge>();


        // Mapping hibernate
        // à completer ....


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
}
