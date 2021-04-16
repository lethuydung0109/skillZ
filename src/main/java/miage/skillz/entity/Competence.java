package miage.skillz.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(	name = "competence")
// Mapping hibernate
// à completer ....
// ...
public class Competence {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        // Id de la compétence pere
        private long id_pere;

        // Nom de la compétence
        private String nom_competence;

    public Competence(String nom_competence, long id_pere) {
        this.nom_competence = nom_competence;
        this.id_pere = id_pere;
    }




}
