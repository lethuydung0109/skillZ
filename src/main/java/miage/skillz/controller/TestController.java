package miage.skillz.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/participant")
    @PreAuthorize("hasRole('PARTICIPANT') or hasRole('CONCEPTEUR') or hasRole('ADMIN')")
    public String participantAccess() {
        return "Participant Content.";
    }

    @GetMapping("/concepteur")
    @PreAuthorize("hasRole('CONCEPTEUR')")
    public String concepteurAccess() {
        return "Concepteur Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
