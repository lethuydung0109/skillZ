package miage.skillz.controller;

import miage.skillz.entity.Competence;
import miage.skillz.service.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class CompetenceController {

    @Autowired
    private CompetenceService service;

    // Créer compétence
    //@PostMapping("/all")
    @PostMapping(value = "/createCompetence",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Competence> createCompetence(@RequestBody Competence competence)
    {
       // User currentUser = this.userController.findById(userDetails.getId());
        return service.createCompetence(competence);
    }

    // Modifier competence .. à completer
    @PutMapping("/all")
    @RequestMapping(value = "/updateCompetence", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public Competence updateCompetence(@RequestBody Competence comp)
    {
        return service.updateCompetence(comp);
    }

    // Supprimer competence
    @DeleteMapping("/all")
    @RequestMapping(value = "/deleteCompetence/{competenceId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCompetence(@PathVariable("competenceId") Long competenceId)
    {
        service.deleteCompetence(competenceId);
    }

    // Liste des compétences
    @GetMapping("/all")
    @RequestMapping(value = "/allCompetence", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Competence> getAllCompetence()
    {
        return service.getAllCompetence();
    }

    // Compétence by ID
    @GetMapping("/all")
    @RequestMapping(value = "/competenceById/{comptenceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Competence getCompetenceById(@PathVariable("comptenceId") Long comptenceId)
    {
        return service.getCompetenceById(comptenceId);
    }

    // Compétence by Id Père
    @GetMapping("/all")
    @RequestMapping(value = "/getCompetenceByIdPere/{IdPere}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Competence>   getCompetenceIdPere(@PathVariable("IdPere") Long IdPere)
    {
        return service.getCompetenceByIdPere(IdPere);
    }


}

