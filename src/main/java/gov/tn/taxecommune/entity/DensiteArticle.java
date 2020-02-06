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
@Table(name = "densite_article")
public class DensiteArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private DensiteArticlePK densiteArticlePK;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeArticle")
    @JoinColumn(name = "num_municipal", referencedColumnName = "num_municipal", insertable = false, updatable = false)
    private Article article;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeDensite")
    @JoinColumn(name = "code_densite", referencedColumnName = "code_densite", insertable = false, updatable = false)
    private Densite densite;

    @Column(name = "ouverture_dArticle")
    private Date ouverturedArticle;

    @Column(name = "encours_dArticle")
    private boolean encoursdArticle;

    @Column(name = "cloture_dArticle")
    private Date cloturedArticle;

    public DensiteArticle() {
    }

    public DensiteArticle(DensiteArticlePK densiteArticlePK, Article article, Densite densite, Date ouverturedArticle, boolean encoursdArticle, Date cloturedArticle) {
        this.densiteArticlePK = densiteArticlePK;
        this.article = article;
        this.densite = densite;
        this.ouverturedArticle = ouverturedArticle;
        this.encoursdArticle = encoursdArticle;
        this.cloturedArticle = cloturedArticle;
    }

    public DensiteArticlePK getDensiteArticlePK() {
        return densiteArticlePK;
    }

    public void setDensiteArticlePK(DensiteArticlePK densiteArticlePK) {
        this.densiteArticlePK = densiteArticlePK;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Densite getDensite() {
        return densite;
    }

    public void setDensite(Densite densite) {
        this.densite = densite;
    }

    public Date getOuverturedArticle() {
        return ouverturedArticle;
    }

    public void setOuverturedArticle(Date ouverturedArticle) {
        this.ouverturedArticle = ouverturedArticle;
    }

    public boolean isEncoursdArticle() {
        return encoursdArticle;
    }

    public void setEncoursdArticle(boolean encoursdArticle) {
        this.encoursdArticle = encoursdArticle;
    }

    public Date getCloturedArticle() {
        return cloturedArticle;
    }

    public void setCloturedArticle(Date cloturedArticle) {
        this.cloturedArticle = cloturedArticle;
    }
    
    
   
}
