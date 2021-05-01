package miage.skillz;

import miage.skillz.entity.*;
import miage.skillz.enumeration.ENiveau;
import miage.skillz.repository.CompetenceRepository;
import miage.skillz.repository.NiveauRepository;
import miage.skillz.repository.RoleRepository;
import miage.skillz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    NiveauRepository niveauRepository;
    @Autowired
    CompetenceRepository competenceRepository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("DB initializes...");
        List<Role> roles = roleRepository.findAll();
        List<User> users = userRepository.findAll();
        List<Niveau> niveaux = niveauRepository.findAll();
        List<Competence> competences = competenceRepository.findAll();

        if(roles.isEmpty()){
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
            roleRepository.save(new Role(ERole.ROLE_CONCEPTEUR));
            roleRepository.save(new Role(ERole.ROLE_PARTICIPANT));
            System.out.println("--- Roles initialized");
        }

        if(niveaux.isEmpty()){
            niveauRepository.save(new Niveau(ENiveau.NIVEAU1));
            niveauRepository.save(new Niveau(ENiveau.NIVEAU2));
            niveauRepository.save(new Niveau(ENiveau.NIVEAU3));
            niveauRepository.save(new Niveau(ENiveau.NIVEAU4));
            System.out.println("--- Niveaux initialized");
        }

        if(competences.isEmpty()) {
            competenceRepository.save(new Competence("Programmation Web",0L));
            competenceRepository.save(new Competence("Modelisation",0L));
            System.out.println("--- Some competences initialized");
        }

        if(users.isEmpty()){

            User admin = new User("admin", "admin@skillz.com", encoder.encode("adminskillz"));
            User concepteur = new User ("concepteur","c.c@gmail.com", encoder.encode("concepteur"));
            User participant = new User ("participant","p.p@gmail.com", encoder.encode("participant"));

            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            Role concepteurRole = roleRepository.findByName(ERole.ROLE_CONCEPTEUR)
                                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            Role participantRole = roleRepository.findByName(ERole.ROLE_PARTICIPANT)
                                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            System.out.println("Role = " + adminRole.toString());
            System.out.println("Role = " + concepteurRole.toString());
            System.out.println("Role = " + participantRole.toString());

            Set<Role> newRole = new HashSet<>();
            newRole.add(adminRole);
            admin.setRoles(newRole);

            Set<Role> newCRole = new HashSet<>();
            newCRole.add(concepteurRole);
            concepteur.setRoles(newCRole);

            Set<Role> newPRole = new HashSet<>();
            newPRole.add(participantRole);
            participant.setRoles(newPRole);

            userRepository.save(admin);
            userRepository.save(concepteur);
            userRepository.save(participant);

            System.out.println("--- Admin user initialized");
            System.out.println("--- Concepteur user initialized");
            System.out.println("--- Participant user initialized");
        }
    }
}
