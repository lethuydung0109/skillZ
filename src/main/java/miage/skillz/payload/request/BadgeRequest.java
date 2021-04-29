package miage.skillz.payload.request;

import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BadgeRequest {

    @NotBlank
    private String competenceId;

    @NotBlank
    private String niveauName;

    private String dateValiation = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

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
