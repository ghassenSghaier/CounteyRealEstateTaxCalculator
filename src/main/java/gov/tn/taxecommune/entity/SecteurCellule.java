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
@Table(name = "secteur_cellule")
public class SecteurCellule implements Serializable {

    private static final long serialVersionUID = 1L;   

    @EmbeddedId
    private SecteurCellulePK secteurCellulePK;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeCellule")
    @JoinColumn(name = "code_cellule", referencedColumnName = "code_cellule", insertable = false, updatable = false)
    private Cellule cellule;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeSecteur")
    @JoinColumn(name = "code_secteur", referencedColumnName = "code_secteur", insertable = false, updatable = false)
    private Secteur secteur;

    @Column(name = "encours_cellule")
    private Boolean encoursCellule;

    @Column(name = "date_ajout")
    private Date dateAjout;

    @Column(name = "date_cloture")
    private Date dateCloture;

    public SecteurCellule() {
    }

    public SecteurCellule(SecteurCellulePK secteurCellulePK, Cellule cellule, Secteur secteur, Boolean encoursCellule, Date dateAjout, Date dateCloture) {
        this.secteurCellulePK = secteurCellulePK;
        this.cellule = cellule;
        this.secteur = secteur;
        this.encoursCellule = encoursCellule;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    public SecteurCellule( Secteur secteur,Cellule cellule, Boolean encoursCellule, Date dateAjout, Date dateCloture) {
        this.cellule = cellule;
        this.secteur = secteur;
        this.encoursCellule = encoursCellule;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }
    

    public SecteurCellulePK getSecteurCellulePK() {
        return secteurCellulePK;
    }

    public void setSecteurCellulePK(SecteurCellulePK secteurCellulePK) {
        this.secteurCellulePK = secteurCellulePK;
    }

    public Cellule getCellule() {
        return cellule;
    }

    public void setCellule(Cellule cellule) {
        this.cellule = cellule;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public Boolean getEncoursCellule() {
        return encoursCellule;
    }

    public void setEncoursCellule(Boolean encoursCellule) {
        this.encoursCellule = encoursCellule;
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
