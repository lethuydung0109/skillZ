package miage.skillz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="poste")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Poste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long posteId;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competenceId")
    private Competence competence;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="niveauId")
    private Niveau niveau;
    private Long scoreMin;

//    @Builder.Default
//    @JsonIgnore
//    @ManyToMany(mappedBy = "listPostes",fetch = FetchType.LAZY)
//    Set<Poste>  listCompetences = new HashSet<>();


    public Poste(String name, Competence competence, Niveau niveau, Long scoreMin) {
        this.name=name;
        this.competence = competence;
        this.niveau = niveau;
        this.scoreMin = scoreMin;
    }
}
