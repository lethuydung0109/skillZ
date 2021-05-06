package miage.skillz.service;
import miage.skillz.repository.CompetenceRepository;
import miage.skillz.entity.Competence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompetenceService {

    @Autowired
    CompetenceRepository competenceRepository;


    // Créer une competence avec un id père
    public ResponseEntity<Competence> createCompetence(Competence competence)
    {
        Competence comp = new Competence(competence.getNom_competence(),competence.getIdPere());
        return new ResponseEntity<>(this.competenceRepository.saveAndFlush(comp), HttpStatus.OK);
    }

    // Modifier une competence
    public ResponseEntity<Competence> updateCompetence(Competence comp)
    {
        System.out.println("Competence à modifier : " + comp);
      Optional<Competence> comUpadate = this.competenceRepository.findById(comp.getId());
      if(comUpadate.isPresent())
      {
          Competence com = comUpadate.get();
          com.setNom_competence(comp.getNom_competence());
          com.setIdPere(comp.getIdPere());
          System.out.println("Competence trouvé!" + com);
          return new ResponseEntity<>(this.competenceRepository.saveAndFlush(com),HttpStatus.OK);
      }
      else
      {
          System.out.println("Error competence pas trouvé!");
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

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
            long idComp =  competence.getIdPere();
            if(idComp == IdPere){
                newList.add(competence);
            }
        }
        return newList;
    }

    public List<Competence> getTreeCompetences ()
    {
        List<Competence> listComp = this.getAllCompetence();
        HashMap<String, Long> listCompetences = new HashMap<>();

        for (Competence competence : listComp) {
            long IdPere =  competence.getIdPere();
            if(IdPere == 0){
                listCompetences.put(competence.getNom_competence(), competence.getIdPere());
            }
        }


        return listComp;
    }



}





