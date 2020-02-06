package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "densite")
public class Densite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code_densite", unique = true)
    private String codeDensité;

    @Column(name = "nom_densite", unique = true)
    private String nomDensité;

    @Column(name = "prix_densité", unique = true)
    private double prixDensité;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "densite", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<DensiteArticle> das;

    public Densite(String codeDensité, String nomDensité, double prixDensité) {
        super();
        this.codeDensité = codeDensité;
        this.nomDensité = nomDensité;
        this.prixDensité = prixDensité;

    }

    public Densite() {
        // TODO Auto-generated constructor stub
    }

    public String getCodeDensité() {
        return codeDensité;
    }

    public void setCodeDensité(String codeDensité) {
        this.codeDensité = codeDensité;
    }

    public double getPrixDensité() {
        return prixDensité;
    }

    public void setPrixDensité(double prixDensité) {
        this.prixDensité = prixDensité;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomDensité() {
        return nomDensité;
    }

    public void setNomDensité(String nomDensitéUrbaine) {
        this.nomDensité = nomDensitéUrbaine;
    }

    public Collection<DensiteArticle> getDas() {
        return das;
    }

    public void setDas(List<DensiteArticle> das) {
        this.das = das;
    }

}
