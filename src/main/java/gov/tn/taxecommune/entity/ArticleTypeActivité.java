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
@Table(name = "article_type_activite")
public class ArticleTypeActivité implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ArticleTypeActivitePK articleTypeActivitePK;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeArticle")
    @JoinColumn(name = "num_municipal", referencedColumnName = "num_municipal", insertable = false, updatable = false)
    private Article article;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeType")
    @JoinColumn(name = "code_type", referencedColumnName = "code_type", insertable = false, updatable = false)
    private TypeActivité typeActivite;

    @Column(name = "ouverture_articletypeactivité")
    private Date ouvertureArticletypeactivite;

    @Column(name = "encours_articletypeactivité")
    private boolean encoursArticletypeactivite;

    @Column(name = "cloture_articleStatutRésidentiel")
    private Date clotureArticletypeactivité;

    public ArticleTypeActivité(Article article, TypeActivité typeActivité, Date ouvertureArticletypeactivité,
            boolean encoursArticletypeactivité, Date clotureArticletypeactivité) {
        super();
        this.article = article;
        this.typeActivite = typeActivité;
        this.ouvertureArticletypeactivite = ouvertureArticletypeactivité;
        this.encoursArticletypeactivite = encoursArticletypeactivité;
        this.clotureArticletypeactivité = clotureArticletypeactivité;
    }

    public ArticleTypeActivité() {
        super();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public TypeActivité getTypeActivite() {
        return typeActivite;
    }

    public void setTypeActivite(TypeActivité typeActivite) {
        this.typeActivite = typeActivite;
    }

    public Date getOuvertureArticletypeactivite() {
        return ouvertureArticletypeactivite;
    }

    public void setOuvertureArticletypeactivite(Date ouvertureArticletypeactivité) {
        this.ouvertureArticletypeactivite = ouvertureArticletypeactivité;
    }

    public boolean isEncoursArticletypeactivite() {
        return encoursArticletypeactivite;
    }

    public void setEncoursArticletypeactivite(boolean encoursArticletypeactivité) {
        this.encoursArticletypeactivite = encoursArticletypeactivité;
    }

    public Date getClotureArticletypeactivité() {
        return clotureArticletypeactivité;
    }

    public void setClotureArticletypeactivité(Date clotureArticletypeactivité) {
        this.clotureArticletypeactivité = clotureArticletypeactivité;
    }

    public ArticleTypeActivitePK getArticleTypeActivitePK() {
        return articleTypeActivitePK;
    }

    public void setArticleTypeActivitePK(ArticleTypeActivitePK articleTypeActivitePK) {
        this.articleTypeActivitePK = articleTypeActivitePK;
    }
    
    

}
