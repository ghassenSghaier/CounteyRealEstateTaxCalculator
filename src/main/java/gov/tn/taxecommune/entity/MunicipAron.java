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
@Table(name = "municip_aron")
public class MunicipAron implements Serializable {

    private static final long serialVersionUID = 1L;   

    @EmbeddedId
    private MunicipAronPK municipAronPK;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeMunicip")
    @JoinColumn(name = "code_municipalite", referencedColumnName = "code_municipalite", insertable = false, updatable = false)
    private Municipalité municip;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeAron")
    @JoinColumn(name = "code_arrondissement", referencedColumnName = "code_arrondissement", insertable = false, updatable = false)
    private Arrondissement aron;

    @Column(name = "encours_prestation")
    private Boolean encoursAron;

    @Column(name = "date_ajout")
    private Date dateAjout;

    @Column(name = "date_cloture")
    private Date dateCloture;

    public MunicipAron() {
    }

    public MunicipAron(MunicipAronPK municipAronPK, Municipalité municip, Arrondissement aron, Boolean encoursAron, Date dateAjout, Date dateCloture) {
        this.municipAronPK = municipAronPK;
        this.municip = municip;
        this.aron = aron;
        this.encoursAron = encoursAron;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    public MunicipAron(Municipalité municip, Arrondissement aron, Boolean encoursAron, Date dateAjout, Date dateCloture) {
        this.municip = municip;
        this.aron = aron;
        this.encoursAron = encoursAron;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }
    

    public MunicipAronPK getMunicipAronPK() {
        return municipAronPK;
    }

    public void setMunicipAronPK(MunicipAronPK municipAronPK) {
        this.municipAronPK = municipAronPK;
    }

    public Municipalité getMunicip() {
        return municip;
    }

    public void setMunicip(Municipalité municip) {
        this.municip = municip;
    }

    public Arrondissement getAron() {
        return aron;
    }

    public void setAron(Arrondissement aron) {
        this.aron = aron;
    }

    public Boolean getEncoursAron() {
        return encoursAron;
    }

    public void setEncoursAron(Boolean encoursAron) {
        this.encoursAron = encoursAron;
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
