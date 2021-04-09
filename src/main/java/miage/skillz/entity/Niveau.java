package miage.skillz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import miage.skillz.enumeration.ENiveau;

import javax.persistence.*;

@Entity
@Table(name="niveau")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Niveau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNiveau;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ENiveau niveau;

}
