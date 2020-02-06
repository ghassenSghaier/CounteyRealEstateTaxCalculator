package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "arrondissement")
public class Arrondissement implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Id
    @NotNull
    @Column(name = "code_arrondissement", length = 6, unique = true)
    private String code;

    @Column(name = "nom_arrondissement", length = 8)
    private String nom;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aron", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<MunicipAron> aronMunicips;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aron", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<AronSecteur> aronSecteurs;

    public Arrondissement(String code, String nom, List<MunicipAron> aronMunicips, List<AronSecteur> aronSecteurs) {
        super();
        this.code = code;
        this.nom = nom;
        this.aronMunicips = aronMunicips;
        this.aronSecteurs = aronSecteurs;
    }

    public Arrondissement(String code, String nom, List<MunicipAron> aronMunicips) {
        super();
        this.code = code;
        this.nom = nom;
        this.aronMunicips = aronMunicips;
    }

    public Arrondissement(String code, String nom) {
        super();
        this.code = code;
        this.nom = nom;
    }

    public Arrondissement() {
        super();
    }

    public Collection<MunicipAron> getAronMunicips() {
        return aronMunicips;
    }

    public void setAronMunicips(List<MunicipAron> aronMunicips) {
        this.aronMunicips = aronMunicips;
    }

    public Collection<AronSecteur> getAronSecteurs() {
        return aronSecteurs;
    }

    public void setAronSecteurs(List<AronSecteur> aronSecteurs) {
        this.aronSecteurs = aronSecteurs;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String identifiant) {
        this.code = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
