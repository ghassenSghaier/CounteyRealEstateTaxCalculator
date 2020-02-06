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
@Table(name = "Article_rue")
public class ArticleRue implements Serializable {

    private static final long serialVersionUID = 1L;    
    
    @EmbeddedId
    protected ArticleRuePK articleruePK;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value="numMunicipal")
    @JoinColumn(name = "num_municipal", referencedColumnName = "num_municipal",insertable = false,updatable = false)    
    private Article article;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value="codeRue")
    @JoinColumn(name = "code_rue", referencedColumnName = "code_rue",insertable = false,updatable = false)    
    private Rue rue;

    @Column(name = "encours_article")
    private Boolean encoursArticle;

    @Column(name = "date_ajout")
    private Date dateAjout;

    @Column(name = "date_cloture")
    private Date dateCloture;

    public ArticleRue() {
    }

    public ArticleRue(ArticleRuePK articleruePK, Article article, Rue rue, Boolean encoursArticle, Date dateAjout, Date dateCloture) {
        this.articleruePK = articleruePK;
        this.article = article;
        this.rue = rue;
        this.encoursArticle = encoursArticle;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }

    public ArticleRue(Article article, Rue rue, Boolean encoursArticle, Date dateAjout, Date dateCloture) {
        this.article = article;
        this.rue = rue;
        this.encoursArticle = encoursArticle;
        this.dateAjout = dateAjout;
        this.dateCloture = dateCloture;
    }
    

    public ArticleRuePK getArticleruePK() {
        return articleruePK;
    }

    public void setArticleruePK(ArticleRuePK articleruePK) {
        this.articleruePK = articleruePK;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Rue getRue() {
        return rue;
    }

    public void setRue(Rue rue) {
        this.rue = rue;
    }

    public Boolean getEncoursArticle() {
        return encoursArticle;
    }

    public void setEncoursArticle(Boolean encoursArticle) {
        this.encoursArticle = encoursArticle;
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
