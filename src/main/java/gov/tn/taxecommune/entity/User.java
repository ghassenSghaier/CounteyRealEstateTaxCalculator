package gov.tn.taxecommune.entity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Collection;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "utilisateur")
//@MappedSuperclass
public class User extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String nom;

    protected String prenom;

    @Column(unique = true)
    protected String username;

    @Column(length = 60)
    protected String password;

    @Column(unique = true)
    protected String email;

    protected boolean enabled;

//    @JsonBackReference
//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(name = "users_roles",
//            joinColumns = {
//                @JoinColumn(name = "user_id")},
//            inverseJoinColumns = {
//                @JoinColumn(name = "role_id")})
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns = {
                @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "role_id")})
    private Collection<Role> roles;

    public User() {
    }

    public User(String nom, String prenom, String username, String email, String password, boolean enabled) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String name, String surname, String username, String email,
            String password, boolean enabled, List<Role> userroles) {
        this.nom = name;
        this.prenom = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = userroles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> userRoles) {
        this.roles = userRoles;
    }

}
