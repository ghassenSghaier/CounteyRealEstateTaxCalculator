package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name ="code_categorie")
public class Categorie implements Serializable {

    @Id
    @NotNull
    @Column(name = "code_categorie", unique = true)
    private String codeCategorie;

    @Column(name = "nom_categorie", unique = true)
    private String nomCategorie;

    @Column(name = "max_couvert")
    private double maxCouvert;

    @Column(name = "min_couvert")
    private double minCouvert;

    @JsonBackReference
    @JoinColumn(name = "code_vocation", referencedColumnName = "code_vocation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vocation vocation;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie",fetch = FetchType.LAZY)
    private Collection<CategorieArticle> categorieArticles;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aCategorie",fetch = FetchType.LAZY)
    private Collection<CategorieTarificationprestation> categorieTarificationprestations;

    public Categorie(String codeCategorie, String nomCategorie, double maxCouvert, double minCouvert,
            Vocation vocation) {
        super();
        this.codeCategorie = codeCategorie;
        this.nomCategorie = nomCategorie;
        this.maxCouvert = maxCouvert;
        this.minCouvert = minCouvert;
        this.vocation = vocation;
    }

    public Categorie(String codeCategorie, String nomCategorie, double maxCouvert, double minCouvert) {
        super();
        this.codeCategorie = codeCategorie;
        this.nomCategorie = nomCategorie;
        this.maxCouvert = maxCouvert;
        this.minCouvert = minCouvert;
    }

    public Categorie() {
        // TODO Auto-generated constructor stub
    }

    public String getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public double getMaxCouvert() {
        return maxCouvert;
    }

    public void setMaxCouvert(double maxCouvert) {
        this.maxCouvert = maxCouvert;
    }

    public double getMinCouvert() {
        return minCouvert;
    }

    public void setMinCouvert(double minCouvert) {
        this.minCouvert = minCouvert;
    }

    public Vocation getVocation() {
        return vocation;
    }

    public void setVocation(Vocation vocation) {
        this.vocation = vocation;
    }

    public Collection<CategorieArticle> getCategorieArticles() {
        return categorieArticles;
    }

    public void setCategorieArticles(Collection<CategorieArticle> categorieArticles) {
        this.categorieArticles = categorieArticles;
    }

    public Collection<CategorieTarificationprestation> getCategorieTarificationprestations() {
        return categorieTarificationprestations;
    }

    public void setCategorieTarificationprestations(Collection<CategorieTarificationprestation> categorieTarificationprestations) {
        this.categorieTarificationprestations = categorieTarificationprestations;
    }
    

}
