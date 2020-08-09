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
@Table(name="categorie_article")
public class CategorieArticle implements Serializable {

   @EmbeddedId
    protected CategorieArticlePK categorieArticlePK;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeArticle")
    @JoinColumn(name = "num_municipal", referencedColumnName = "num_municipal", insertable = false, updatable = false)
    private Article article;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeCategorie")
    @JoinColumn(name = "code_categorie", referencedColumnName = "code_categorie", insertable = false, updatable = false)
    private Categorie categorie;


    @Column(name = "ouverture_vocation")
    private Date ouvertureVocation;

    @Column(name = "encours_vocation")
    private boolean encoursVocation;

    @Column(name = "cloture_vocation")
    private Date clotureVocation;

    public CategorieArticle(Article article, Categorie categorie, Date ouvertureVocation, boolean encoursVocation,
            Date clotureVocation) {
        super();
        this.article = article;
        this.categorie = categorie;
        this.ouvertureVocation = ouvertureVocation;
        this.encoursVocation = encoursVocation;
        this.clotureVocation = clotureVocation;
    }

    public CategorieArticle() {
        super();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Date getOuvertureVocation() {
        return ouvertureVocation;
    }

    public void setOuvertureVocation(Date ouvertureVocation) {
        this.ouvertureVocation = ouvertureVocation;
    }

    public boolean isEncoursVocation() {
        return encoursVocation;
    }

    public void setEncoursVocation(boolean encoursVocation) {
        this.encoursVocation = encoursVocation;
    }

    public Date getClotureVocation() {
        return clotureVocation;
    }

    public void setClotureVocation(Date clotureVocation) {
        this.clotureVocation = clotureVocation;
    }

    public CategorieArticlePK getCategorieArticlePK() {
        return categorieArticlePK;
    }

    public void setCategorieArticlePK(CategorieArticlePK categorieArticlePK) {
        this.categorieArticlePK = categorieArticlePK;
    }
    
    

}
