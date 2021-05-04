package miage.skillz.models;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PosteImpl {

    private Long posteId;
    private String name;
    private Long competenceId;
    private Long niveauId;
    private Long scoreMin;

    public PosteImpl(String name, Long competenceId, Long niveauId, Long scoreMin) {
        this.name=name;
        this.competenceId = competenceId;
        this.niveauId = niveauId;
        this.scoreMin = scoreMin;
    }
}
