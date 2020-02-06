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
@Table(name = "article_prestation")
public class ArticlePrestation implements Serializable {

    private static final long serialVersionUID = 1L;    

    @EmbeddedId
    private ArticlePrestationPK articlePrestationPK;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeArticle")
    @JoinColumn(name = "num_municipal", referencedColumnName = "num_municipal", insertable = false, updatable = false)
    private Article article;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codePrestation")
    @JoinColumn(name = "code_prestation", referencedColumnName = "code_prestation", insertable = false, updatable = false)
    private Prestation prestation;

    @Column(name = "encours_prestation")
    private Boolean encoursPrestation;

    @Column(name = "date_ajout")
    private Date dateAjout;

    @Column(name = "date_cloture")
    private Date dateCloture;

    public ArticlePrestation(Article article, gov.tn.taxecommune.entity.Prestation prestation,
            Boolean encoursPrestation, Date dateAjout, Date dateCloture) {
        super();
        this.article = article;
        this.prestation = prestation;
        this.encoursPrestation = encoursPrestation;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    public ArticlePrestation() {
        super();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Prestation getPrestation() {
        return prestation;
    }

    public void setPrestation(Prestation prestation) {
        this.prestation = prestation;
    }

    public Boolean getEncoursPrestation() {
        return encoursPrestation;
    }

    public void setEncoursPrestation(Boolean encoursPrestation) {
        this.encoursPrestation = encoursPrestation;
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
