package miage.skillz.security.services;
import miage.skillz.repository.CompetenceRepository;
import miage.skillz.entity.Competence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompetenceService {

    @Autowired
    CompetenceRepository competenceRepository;


    // Créer une competence avec un id père
    public ResponseEntity<Competence> createCompetence(Competence competence)
    {
        Competence comp = new Competence(competence.getNom_competence(),competence.getId_pere());
        return new ResponseEntity<Competence>(this.competenceRepository.saveAndFlush(comp), HttpStatus.OK);
    }

    // Update competence .. à completer
    public Competence updateCompetence(Competence comp)
    {
        //log.info("Competence à modifier : " + comp);
        Competence competence = new Competence(comp.getId(), comp.getId_pere(), comp.getNom_competence());
        return competence;
    }

    // Supprimer competence
    public void deleteCompetence(Long competenceId)
    {
     //System.out.println("delete : "+competenceRepository.findById(competenceId).toString());
        competenceRepository.deleteById(competenceId);
    }

    // Retourne la liste des competences
    public List<Competence> getAllCompetence() {
        return competenceRepository.findAll();
    }

    // Compétence by ID
    public Competence getCompetenceById(Long competenceId) {
        return this.competenceRepository.findById(competenceId).orElse(null);
    }

    // Liste competence by Id père
    public List<Competence> getCompetenceByIdPere(Long IdPere){
        List<Competence> comptences=  this.getAllCompetence();
        List<Competence> newList= new ArrayList<>();
        for (Competence competence : comptences) {
            Long idComp =  competence.getId_pere();
            if(idComp == IdPere){
                newList.add(competence);
            }
        }
        return newList;
    }

}





