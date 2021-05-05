package miage.skillz.controller;

import miage.skillz.entity.Poste;
import miage.skillz.entity.Question;
import miage.skillz.entity.User;
import miage.skillz.models.PosteImpl;
import miage.skillz.models.QuestionImpl;
import miage.skillz.security.services.UserDetailsImpl;
import miage.skillz.service.PosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api")
public class PosteController {

    @Autowired
    private UserController userController;

    @Autowired
    private PosteService posteService;

    @PostMapping(value = "/createPoste", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Poste> createPoste(@RequestBody PosteImpl posteImpl)
    {
        return  posteService.createPoste(posteImpl);
    }

    @PutMapping(value = "/updatePoste", consumes = "application/json",produces = "application/json")
    public ResponseEntity<Poste> updateQuestion(@RequestBody PosteImpl posteImpl)
    {
        return  posteService.updatePoste(posteImpl);
    }

    @GetMapping(value = "/getCandidatesForThePost/{posteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<User> getCandidatesForThePost(@PathVariable  Long posteId)
    {
        return this.posteService.getCandidatesForThePost(posteId);
    }

    @GetMapping(value = "/allPostes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Poste> getAllPostes()
    {
        return  posteService.getAllPostes();
    }

    @DeleteMapping(value = "/deletePoste/{posteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteQuestion(@PathVariable Long posteId)
    {
        posteService.deletePoste(posteId);
    }
}
