package gov.tn.taxecommune.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "municipalite")
public class Municipalité implements Serializable {

    private static final long serialVersionUID = 1L;   

    @Id
    @NotNull
    @Column(name = "code_municipalite", length = 6, unique = true)
    private String codeMunicip;

    @Column(name = "nom_municipalite", length = 20, unique = true)
    private String nom;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "municip",fetch = FetchType.EAGER, orphanRemoval = true)
    private Collection<MunicipAron> arons;

    public Municipalité() {
    }

    public Municipalité(String codeMunicip, String nom, Collection<MunicipAron> arons) {
        this.codeMunicip = codeMunicip;
        this.nom = nom;
        this.arons = arons;
    }

    public String getCodeMunicip() {
        return codeMunicip;
    }

    public void setCodeMunicip(String codeMunicip) {
        this.codeMunicip = codeMunicip;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<MunicipAron> getArons() {
        return arons;
    }

    public void setArons(Collection<MunicipAron> arons) {
        this.arons = arons;
    }
    
    
    
    
   
}
