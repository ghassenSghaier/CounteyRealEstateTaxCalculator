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
@Table(name = "secteur")
public class Secteur implements Serializable {

    private static final long serialVersionUID = 1L;   

    @Id
    @NotNull
    @Column(name = "code_secteur", length = 6, unique = true)
    private String code;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "secteur", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<SecteurCellule> secteurCellules;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "secteur", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<AronSecteur> secteurArons;

    public Secteur(String code, Collection<SecteurCellule> secteurCellules, Collection<AronSecteur> secteurArons) {
        super();
        this.code = code;
        this.secteurCellules = secteurCellules;
        this.secteurArons = secteurArons;
    }

    public Secteur(String code, List<SecteurCellule> secteurCellules) {
        super();
        this.code = code;
        this.secteurCellules = secteurCellules;
    }

    public Secteur(String code) {
        super();
        this.code = code;
    }

    public Secteur() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String identifiant) {
        this.code = identifiant;
    }

}
