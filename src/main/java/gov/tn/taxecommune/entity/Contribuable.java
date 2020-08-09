package gov.tn.taxecommune.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name="contribuable")
//@PrimaryKeyJoinColumn(name="id")
public class Contribuable extends User implements Serializable {

    @Column(name = "code_contribuable", unique = true)
    protected String identifiant;

    @Column(name = "nom_contribuable")
    protected String nom;

    @Column(name = "prenom_contribuable")
    protected String prenom;

    @Column(name = "statut_contribuable", columnDefinition = "ENUM('PersonnePhysique', 'PersonneMorale')")
    protected String statut;

    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "adresse", length = 12)
    private String adresse;

    @Column(name = "ville", length = 12)
    private String ville;

    @Column(name = "code_postal", length = 4)
    private String codePostal;

    @Column(name = "numero_tel", length = 8)
    private long numeroTel;

    @OneToOne
    private Identifiant identite;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<ContribuableArticle> contribuableArticles;

    public Contribuable() {
        super();
    }

    public Contribuable(String username, String password, String identifiant, String nom, String prenom, String statut,
            @Email String email, String adresse, String ville, String codePostal, long numeroTel,
            List<ContribuableArticle> contribuableArticle) {
        super(nom, prenom, username, email, password, false);
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.statut = statut;
        this.email = email;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numeroTel = numeroTel;
        this.contribuableArticles = contribuableArticle;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public long getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(long numeroTel) {
        this.numeroTel = numeroTel;
    }    

    public Identifiant getIdentite() {
        return identite;
    }

    public void setIdentite(Identifiant identite) {
        this.identite = identite;
    }

    

    public Collection<ContribuableArticle> getContribuableArticles() {
        return contribuableArticles;
    }

    public void setContribuableArticles(Collection<ContribuableArticle> contribuableArticles) {
        this.contribuableArticles = contribuableArticles;
    }

}
