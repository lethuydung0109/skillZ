package miage.skillz.payload.reponse;

public class StatsUserResponse {
    int nbUtilisateur;
    int nbParticipants;
    int nbConcepteurs;

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
}
