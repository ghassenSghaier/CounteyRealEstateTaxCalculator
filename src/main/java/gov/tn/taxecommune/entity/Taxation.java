package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="taxation")
public class Taxation implements Serializable {

    private static final long serialVersionUID = 1L;   

    @Id
    @NotNull
    @Column(name = "code_taxation", unique = true)
    private String code;

    @Column(name = "montant_fnah", nullable = false)
    private double montantFNAH;

    @Column(name = "montant_ttib", nullable = false)
    private double montantTIB;

    @Column(name = "montant_ttcl", nullable = false)
    private double montantTCL;

    @Column(name = "montant_ttnb", nullable = false)
    private double montantTTNB;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId(value = "num_municipal")
    @JoinColumn(name = "num_municipal", referencedColumnName = "num_municipal", insertable = false, updatable = false)
    private Article article;

    private Date anneeTaxation;

    public Taxation(Article article, Date anneeTaxation, double montantFNAH, double montantTIB, double montantTCL,
            double montantTTNB) {
        super();
        this.article = article;
        this.anneeTaxation = anneeTaxation;
        this.montantFNAH = montantFNAH;
        this.montantTIB = montantTIB;
        this.montantTCL = montantTCL;
        this.montantTTNB = montantTTNB;
    }

    public Taxation() {
        // TODO Auto-generated constructor stub
    }   

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getAnneeTaxation() {
        return anneeTaxation;
    }

    public void setAnneeTaxation(Date anneeTaxation) {
        this.anneeTaxation = anneeTaxation;
    }

    public double getMontantFNAH() {
        return montantFNAH;
    }

    public void setMontantFNAH(double montantFNAH) {
        this.montantFNAH = montantFNAH;
    }

    public double getMontantTIB() {
        return montantTIB;
    }

    public void setMontantTIB(double montantTIB) {
        this.montantTIB = montantTIB;
    }

    public double getMontantTCL() {
        return montantTCL;
    }

    public void setMontantTCL(double montantTCL) {
        this.montantTCL = montantTCL;
    }

    public double getMontantTTNB() {
        return montantTTNB;
    }

    public void setMontantTTNB(double montantTTNB) {
        this.montantTTNB = montantTTNB;
    }

}
