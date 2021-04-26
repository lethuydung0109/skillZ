package miage.skillz.payload.reponse;

import miage.skillz.entity.Competence;

import java.util.List;

public class UserResponse {
    String username;
    String email;
    List<String> roles;
    List<String> competences;
//    List<Badge> badges;

    public UserResponse() {
    }

    public UserResponse(String username, String email, List<String> roles) {
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
