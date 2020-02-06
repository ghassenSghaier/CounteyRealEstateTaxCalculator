package gov.tn.taxecommune.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "cellule_rue")
public class CelluleRue implements Serializable {

    private static final long serialVersionUID = 1L;    

    @EmbeddedId
    private CelluleRuePK celluleRuePK;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeCellule")
    @JoinColumn(name = "code_cellule", referencedColumnName = "code_cellule", insertable = false, updatable = false)
    private Cellule cellule;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeRue")
    @JoinColumn(name = "code_rue", referencedColumnName = "code_rue", insertable = false, updatable = false)
    private Rue rue;

    @Column(name = "encours_rue")
    private Boolean encoursRue;

    @Column(name = "date_ajout")
    private Date dateAjout;

    @Column(name = "date_cloture")
    private Date dateCloture;

    public CelluleRue(Cellule cellule, Rue rue, Boolean encoursRue, Date dateAjout, Date dateCloture) {
        super();
        this.cellule = cellule;
        this.rue = rue;
        this.encoursRue = encoursRue;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    public CelluleRue(Rue rue, Boolean encoursRue, Date dateAjout, Date dateCloture) {
        super();
        this.rue = rue;
        this.encoursRue = encoursRue;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    public CelluleRue(Cellule cellule, Boolean encoursRue, Date dateAjout, Date dateCloture) {
        super();
        this.cellule = cellule;
        this.encoursRue = encoursRue;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    public CelluleRue() {
        super();
    }   

    public CelluleRue(CelluleRuePK celluleRuePK, Cellule cellule, Rue rue, Boolean encoursRue, Date dateAjout, Date dateCloture) {
        this.celluleRuePK = celluleRuePK;
        this.cellule = cellule;
        this.rue = rue;
        this.encoursRue = encoursRue;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    

    public Cellule getCellule() {
        return cellule;
    }

    public void setCellule(Cellule cellule) {
        this.cellule = cellule;
    }

    public Rue getRue() {
        return rue;
    }

    public void setRue(Rue rue) {
        this.rue = rue;
    }

    public Boolean getEncoursRue() {
        return encoursRue;
    }

    public void setEncoursRue(Boolean encoursRue) {
        this.encoursRue = encoursRue;
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
