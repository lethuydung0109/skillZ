package miage.skillz.controller;

import miage.skillz.entity.*;
import miage.skillz.payload.reponse.MessageResponse;
import miage.skillz.payload.reponse.StatsUserResponse;
import miage.skillz.payload.reponse.UserResponse;
import miage.skillz.payload.request.SignupRequest;
import miage.skillz.repository.BadgeRepository;
import miage.skillz.repository.UserRepository;
import miage.skillz.service.CompetenceService;
import miage.skillz.service.RecommendationService;
import miage.skillz.service.RoleService;
import miage.skillz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api")
public class UserController {
    Logger logger =  LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CompetenceService competenceService;

    @Autowired
    private RecommendationService recommendationService;
    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping(value = "/user/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStats(){

        //Number of users
        List<User> allUsers = service.findAll();
        int nbUtilisateurs = allUsers.size();

        //Number of participants
        ArrayList<User> participantsResponse = new ArrayList<>();
        for(User user : allUsers){
            for(Role role : user.getRoles()){
                if(role.getName().toString() == ("ROLE_PARTICIPANT")){
                    // usersResponse[allUsers.get()] = new List<User>();
                    participantsResponse.add(user) ;
                }
            }
        }
        int nbParticipants = participantsResponse.size();

        //Number of concepteurs
        ArrayList<User> concepteursResponse = new ArrayList<>();
        for(User user : allUsers){
            for(Role role : user.getRoles()){
                if(role.getName().toString().equals("ROLE_CONCEPTEUR")){
                    // usersResponse[allUsers.get()] = new List<User>();
                    concepteursResponse.add(user) ;
                }
            }
        }
        int nbConcepteurs = concepteursResponse.size();


        return new  ResponseEntity <StatsUserResponse >(new StatsUserResponse(nbUtilisateurs, nbParticipants, nbConcepteurs), HttpStatus.OK);
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers(){
        List<User> allUsers = service.findAll();
        List<UserResponse> allUserResponse = new ArrayList<>();
        for (User user: allUsers){
            if(!(user.getRoles().isEmpty())){
                allUserResponse.add(new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles().iterator().next().getName().toString()));
            }else{
                allUserResponse.add(new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        ""));
            }
        }
        return new  ResponseEntity <List <UserResponse> >(allUserResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/user/participant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllParticipants(){
        List<User> allUsers = service.findAll();
        ArrayList<User> participantsResponse = new ArrayList<User>();
        for(User user : allUsers){
            for(Role role : user.getRoles()){
                if(role.getName().toString() == ("ROLE_PARTICIPANT")){
                    participantsResponse.add(user) ;
                }
            }
        }
        return new  ResponseEntity <List <User> >(participantsResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/user/concepteur", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllConcepteurs(){
        List<User> allUsers = service.findAll();
//        List<User> concepteursResponse = allUsers;
        ArrayList<User> concepteursResponse = new ArrayList<>();
        for(User user : allUsers){
            for(Role role : user.getRoles()){
                if(role.getName().toString() == ("ROLE_CONCEPTEUR")){
                    // usersResponse[allUsers.get()] = new List<User>();
                    concepteursResponse.add(user) ;
                }
            }
        }
        return new  ResponseEntity <List <User> >(concepteursResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (service.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (service.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.ROLE_PARTICIPANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "concepteur":
                        Role modRole = roleService.findByName(ERole.ROLE_CONCEPTEUR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleService.findByName(ERole.ROLE_PARTICIPANT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        User returnUser = service.saveUser(user);

//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        return new ResponseEntity<User>(returnUser, HttpStatus.OK);
    }


    @PutMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody User user){
        service.saveUser(user);
        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }

    @DeleteMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@PathVariable ("userId") Long userId){
        service.deleteById(userId);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

    @PostMapping(value = "/user/{useId}/badge", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBadgeToUser(@RequestBody Badge badge, @PathVariable ("userId") Long userId ){
        User user = service.findById(userId);
        Set<Badge> badges = user.getBadges();
        badges.add(badge);
        user.setBadges(badges);
        return ResponseEntity.ok(new MessageResponse("Badge added successfully!"));
    }

    @GetMapping(value = "/user/competence/{competenceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getParticipantByCompetence(@PathVariable ("competenceId") Long competenceId ){
        Competence competence = competenceService.getCompetenceById(competenceId);

        Set<Badge> badges = competence.getListBadges();
        Set<User> listUsers = new HashSet<>();
        for (Badge badge: badges){
            listUsers.add(badge.getUser());
        }
        return new  ResponseEntity <>(listUsers, HttpStatus.OK);
    }

    @PostMapping(value = "/user/recommendation/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> writeRecommendation(@RequestBody Badge badge, @PathVariable ("userId") Long userId ){
        User user = service.findById(userId);
        Set<Badge> badges = user.getBadges();
        badges.add(badge);
        user.setBadges(badges);
        return ResponseEntity.ok(new MessageResponse("Badge added successfully!"));
    }

    @GetMapping(value = "/user/{userId}/recommendation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllRecommendations(@PathVariable ("userId") Long userId ){
        User user = service.findById(userId);
        Set<Recommendation> recommendations = user.getRecommendationsByOthers();
        return new  ResponseEntity <>(recommendations, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable ("userId") Long userId ){
        User user = service.findById(userId);
        return new  ResponseEntity <> (user, HttpStatus.OK);
    }

    @GetMapping(value = "/user/name/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findUserbyName(@PathVariable ("username") String userName ){
        List<User> users = service.findByUsernameContaining(userName).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + userName));;
        logger.info("Users found = " + users.toString());
        List<UserResponse> listUserResponse = new ArrayList<>();
        for(User user: users){
            if(!user.getRoles().isEmpty()){
                listUserResponse.add(new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRoles().iterator().next().getName().toString()
                ));
            }else{
                listUserResponse.add(new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        ""
                ));
            }

        }

        return new  ResponseEntity <List<UserResponse>> (listUserResponse, HttpStatus.OK);
    }
//    @DeleteMapping(value = "/user/recommendation/{recommendationId}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> deleteRecommendation(@PathVariable ("recommendationId") Long recommendationId){
//        Recommendation recommendation = recommendationService.findRecommendationById(recommendationId);
//        Set<Recommendation> recommendations =
//        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
//    }


    @RequestMapping(value = "/findById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User findById(Long userID) {
        return service.findById(userID);
    }
}
