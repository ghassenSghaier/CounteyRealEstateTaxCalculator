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

@Entity
public class CategorieTarificationprestation implements Serializable {

    private static final long serialVersionUID = 1L;    

    @EmbeddedId
    private CategorieTarificationprestationPK categorieTarificationprestationPK;

    @Column(name = "nom_ctPrestation", unique = true)
    private String nomCTPrestation;

    @Column(name = "code_ctPrestation", unique = true)
    private String codeCTPrestation;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeCategorie")
    @JoinColumn(name = "code_categorie", referencedColumnName = "code_categorie", insertable = false, updatable = false)
    private Categorie aCategorie;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeTarification")
    @JoinColumn(name = "code_tarification", referencedColumnName = "code_tarification", insertable = false, updatable = false)
    private Tarificationprestation tPrestation;

    @Column(name = "encours_ctPrestation")
    private Boolean encoursctPrestation;

    @Column(name = "taxe_reference")
    private double taxeReference;

    @Column(name = "prixMax_reference")
    private double prixMaxReference;

    @Column(name = "prixMin_reference")
    private double prixMinReference;

    @Column(name = "date_ajout")
    private Date dateAjout;

    @Column(name = "date_cloture")
    private Date dateCloture;

    public CategorieTarificationprestation() {
        super();
    }

    public String getNomCTPrestation() {
        return nomCTPrestation;
    }

    public void setNomCTPrestation(String nomCTPrestation) {
        this.nomCTPrestation = nomCTPrestation;
    }

    public String getCodeCTPrestation() {
        return codeCTPrestation;
    }

    public void setCodeCTPrestation(String codeCTPrestation) {
        this.codeCTPrestation = codeCTPrestation;
    }

    public Categorie getaCategorie() {
        return aCategorie;
    }

    public void setaCategorie(Categorie aCategorie) {
        this.aCategorie = aCategorie;
    }

    public Tarificationprestation gettPrestation() {
        return tPrestation;
    }

    public void settPrestation(Tarificationprestation tPrestation) {
        this.tPrestation = tPrestation;
    }

    public Boolean getEncoursctPrestation() {
        return encoursctPrestation;
    }

    public void setEncoursctPrestation(Boolean encoursctPrestation) {
        this.encoursctPrestation = encoursctPrestation;
    }

    public double getTaxeReference() {
        return taxeReference;
    }

    public void setTaxeReference(double taxeReference) {
        this.taxeReference = taxeReference;
    }

    public double getPrixMaxReference() {
        return prixMaxReference;
    }

    public void setPrixMaxReference(double prixMaxReference) {
        this.prixMaxReference = prixMaxReference;
    }

    public double getPrixMinReference() {
        return prixMinReference;
    }

    public void setPrixMinReference(double prixMinReference) {
        this.prixMinReference = prixMinReference;
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

    public CategorieTarificationprestationPK getCategorieTarificationprestationPK() {
        return categorieTarificationprestationPK;
    }

    public void setCategorieTarificationprestationPK(CategorieTarificationprestationPK categorieTarificationprestationPK) {
        this.categorieTarificationprestationPK = categorieTarificationprestationPK;
    }
    
    

}
