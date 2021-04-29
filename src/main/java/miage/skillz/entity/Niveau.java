package miage.skillz.entity;

import lombok.*;
import miage.skillz.enumeration.ENiveau;

import javax.persistence.*;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Niveau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long niveauId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ENiveau niveau;

    public Niveau() { }

    public Niveau(ENiveau niveau) {
        this.niveau = niveau;
    }

    public Long getNiveauId() {
        return niveauId;
    }

    public void setNiveauId(Long niveauId) {
        this.niveauId = niveauId;
    }

    public ENiveau getNiveau() {
        return niveau;
    }

    public void setNiveau(ENiveau niveau) {
        this.niveau = niveau;
    }
}
