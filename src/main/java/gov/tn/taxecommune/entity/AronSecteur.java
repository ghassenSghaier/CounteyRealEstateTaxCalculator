package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "aron_secteur")
public class AronSecteur implements Serializable {

    private static final long serialVersionUID = 1L;    

    @EmbeddedId
    private AronSecteurPK aronSecteurPK;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeAron")
    @JoinColumn(name = "code_arrondissement", referencedColumnName = "code_arrondissement", insertable = false, updatable = false)
    private Arrondissement aron;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeSecteur")
    @JoinColumn(name = "code_secteur", referencedColumnName = "code_secteur", insertable = false, updatable = false)
    private Secteur secteur;

    @Column(name = "encours_secteur")
    private Boolean encoursSecteur;

    @Column(name = "date_ajout")
    private Date dateAjout;

    @Column(name = "date_cloture")
    private Date dateCloture;

    public AronSecteur(Arrondissement aron, Secteur secteur, Boolean encoursSecteur, Date dateAjout, Date dateCloture) {
        super();
        this.aron = aron;
        this.secteur = secteur;
        this.encoursSecteur = encoursSecteur;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    public AronSecteur(Secteur secteur, Boolean encoursSecteur, Date dateAjout, Date dateCloture) {
        super();
        this.secteur = secteur;
        this.encoursSecteur = encoursSecteur;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    public AronSecteur(Arrondissement aron, Boolean encoursSecteur, Date dateAjout, Date dateCloture) {
        super();
        this.aron = aron;
        this.encoursSecteur = encoursSecteur;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    public AronSecteur(AronSecteurPK aronSecteurPK, Arrondissement aron, Secteur secteur, Boolean encoursSecteur, Date dateAjout, Date dateCloture) {
        this.aronSecteurPK = aronSecteurPK;
        this.aron = aron;
        this.secteur = secteur;
        this.encoursSecteur = encoursSecteur;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    
    public AronSecteur() {
        super();
    }

    public Arrondissement getAron() {
        return aron;
    }

    public void setAron(Arrondissement aron) {
        this.aron = aron;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public Boolean getEncoursSecteur() {
        return encoursSecteur;
    }

    public void setEncoursSecteur(Boolean encoursSecteur) {
        this.encoursSecteur = encoursSecteur;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Date getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(Date dateCloture) {
        this.dateCloture = dateCloture;
    }

}
