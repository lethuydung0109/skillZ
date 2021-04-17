package miage.skillz.payload.request;

import javax.validation.constraints.NotBlank;

public class BadgeRequest {

    @NotBlank
    private String competenceId;

    @NotBlank
    private String niveauName;

    public String getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(String competenceId) {
        this.competenceId = competenceId;
    }

    public String getNiveauName() {
        return niveauName;
    }

    public void setNiveauName(String niveau) {
        this.niveauName = niveau;
    }
}
