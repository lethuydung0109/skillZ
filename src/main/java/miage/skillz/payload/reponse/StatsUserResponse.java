package miage.skillz.payload.reponse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StatsUserResponse {
    int nbUtilisateur;
    int nbParticipants;
    int nbConcepteurs;

    private String dateValiation = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    public StatsUserResponse() {
    }

    public StatsUserResponse(int nbUtilisateur, int nbParticipants, int nbConcepteurs) {
        this.nbUtilisateur = nbUtilisateur;
        this.nbParticipants = nbParticipants;
        this.nbConcepteurs = nbConcepteurs;
    }

    public int getNbUtilisateur() {
        return nbUtilisateur;
    }

    public void setNbUtilisateur(int nbUtilisateur) {
        this.nbUtilisateur = nbUtilisateur;
    }

    public int getNbParticipants() {
        return nbParticipants;
    }

    public void setNbParticipants(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    public int getNbConcepteurs() {
        return nbConcepteurs;
    }

    public void setNbConcepteurs(int nbConcepteurs) {
        this.nbConcepteurs = nbConcepteurs;
    }

    public String getDateValiation() {
        return dateValiation;
    }

    public void setDateValiation(String dateValiation) {
        this.dateValiation = dateValiation;
    }
}
