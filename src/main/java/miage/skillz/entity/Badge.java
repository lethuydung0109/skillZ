package miage.skillz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import miage.skillz.enumeration.ENiveau;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "badges")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_competence")
    private Competence competence;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_niveau")
    private Niveau niveau;

    @ManyToMany(mappedBy = "badges")
    @JsonIgnore
    private Set<User> users = new HashSet<User>();


    //Date validation
    private Long dateValiation;

    public Badge() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}






