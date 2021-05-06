package miage.skillz.controller;

import miage.skillz.entity.Competence;
import miage.skillz.service.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api")
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    //Get competence stats
    @GetMapping(value = "/competenceStats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCompetenceStats(){
        List<Competence> allCompetences = competenceService.getAllCompetence();
        int nbCompetences = allCompetences.size();
        return new  ResponseEntity <Integer>(nbCompetences, HttpStatus.OK);
    }

    // Créer compétence
    //@PostMapping("/all")
    @PostMapping(value = "/createCompetence",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Competence> createCompetence(@RequestBody Competence competence)
    {
       // User currentUser = this.userController.findById(userDetails.getId());
        return competenceService.createCompetence(competence);
    }

    // Modifier competence .. à completer
   // @PutMapping("/all")
    @PutMapping(value = "/updateCompetence", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Competence> updateCompetence(@RequestBody Competence competence)
    {
        return competenceService.updateCompetence(competence);
    }

    // Supprimer competence
    @DeleteMapping("/all")
    @RequestMapping(value = "/deleteCompetence/{competenceId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCompetence(@PathVariable("competenceId") Long competenceId)
    {
        competenceService.deleteCompetence(competenceId);
    }

    // Liste des compétences
    @GetMapping("/all")
    @RequestMapping(value = "/allCompetence", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Competence> getAllCompetence()
    {
        return competenceService.getAllCompetence();
    }

    // Compétence by ID
    @GetMapping("/all")
    @RequestMapping(value = "/competenceById/{comptenceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Competence getCompetenceById(@PathVariable("comptenceId") Long comptenceId)
    {
        return competenceService.getCompetenceById(comptenceId);
    }

    // Compétence by Id Père
    @GetMapping("/all")
    @RequestMapping(value = "/getCompetenceByIdPere/{IdPere}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Competence>   getCompetenceIdPere(@PathVariable("IdPere") Long IdPere)
    {
        return competenceService.getCompetenceByIdPere(IdPere);
    }



}

