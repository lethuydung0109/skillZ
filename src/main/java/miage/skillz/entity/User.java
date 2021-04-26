package miage.skillz.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_badges",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "badge_id"))
    private Set<Badge> badges = new HashSet<>();

    //List of recommendations for others
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="writer")
    Set<Recommendation> recommendationsForOthers = new HashSet<Recommendation>();

    //List of recommendations written by orthers
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="receiver")
    Set<Recommendation> recommendationsByOthers = new HashSet<Recommendation>();

    public User(){

    }

    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Badge> getBadges() {
        return badges;
    }

    public void setBadges(Set<Badge> badges) {
        this.badges = badges;
    }

    public Set<Recommendation> getRecommendationsForOthers() {
        return recommendationsForOthers;
    }

    public void setRecommendationsForOthers(Set<Recommendation> recommendationsForOthers) {
        this.recommendationsForOthers = recommendationsForOthers;
    }

    public Set<Recommendation> getRecommendationsByOthers() {
        return recommendationsByOthers;
    }

    public void setRecommendationsByOthers(Set<Recommendation> recommendationsByOthers) {
        this.recommendationsByOthers = recommendationsByOthers;
    }
}
