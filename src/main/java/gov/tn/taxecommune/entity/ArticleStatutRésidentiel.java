package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "article_statut_residentiel")
public class ArticleStatutRésidentiel implements Serializable {

    private static final long serialVersionUID = 1L;    

    @EmbeddedId
    protected ArticleStatutResidentielPK articleStatutResidentielPK;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "numMunicipal")
    @JoinColumn(name = "num_municipal", referencedColumnName = "num_municipal", insertable = false, updatable = false)
    private Article article;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeStatut")
    @JoinColumn(name = "code_statut", referencedColumnName = "code_statut", insertable = false, updatable = false)
    private StatutRésidentiel statutResidentiel;

    @Column(name = "ouverture_articleStatutRésidentiel")
    private Date ouvertureVocation;

    @Column(name = "encours_articleStatutRésidentiel")
    private boolean encoursArticleStatutRésidentiel;

    @Column(name = "cloture_articleStatutRésidentiel")
    private Date clotureArticleStatutRésidentiel;

    public ArticleStatutRésidentiel() {
    }

    public ArticleStatutRésidentiel(ArticleStatutResidentielPK articleStatutResidentielPK, Article article, StatutRésidentiel statutResidentiel, Date ouvertureVocation, boolean encoursArticleStatutRésidentiel, Date clotureArticleStatutRésidentiel) {
        this.articleStatutResidentielPK = articleStatutResidentielPK;
        this.article = article;
        this.statutResidentiel = statutResidentiel;
        this.ouvertureVocation = ouvertureVocation;
        this.encoursArticleStatutRésidentiel = encoursArticleStatutRésidentiel;
        this.clotureArticleStatutRésidentiel = clotureArticleStatutRésidentiel;
    }

    public ArticleStatutRésidentiel(Article article, StatutRésidentiel statutResidentiel, Date ouvertureVocation, boolean encoursArticleStatutRésidentiel, Date clotureArticleStatutRésidentiel) {
        this.article = article;
        this.statutResidentiel = statutResidentiel;
        this.ouvertureVocation = ouvertureVocation;
        this.encoursArticleStatutRésidentiel = encoursArticleStatutRésidentiel;
        this.clotureArticleStatutRésidentiel = clotureArticleStatutRésidentiel;
    }
    
    

    public ArticleStatutResidentielPK getArticleStatutResidentielPK() {
        return articleStatutResidentielPK;
    }

    public void setArticleStatutResidentielPK(ArticleStatutResidentielPK articleStatutResidentielPK) {
        this.articleStatutResidentielPK = articleStatutResidentielPK;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public StatutRésidentiel getStatutResidentiel() {
        return statutResidentiel;
    }

    public void setStatutResidentiel(StatutRésidentiel statutResidentiel) {
        this.statutResidentiel = statutResidentiel;
    }

    public Date getOuvertureVocation() {
        return ouvertureVocation;
    }

    public void setOuvertureVocation(Date ouvertureVocation) {
        this.ouvertureVocation = ouvertureVocation;
    }

    public boolean isEncoursArticleStatutRésidentiel() {
        return encoursArticleStatutRésidentiel;
    }

    public void setEncoursArticleStatutRésidentiel(boolean encoursArticleStatutRésidentiel) {
        this.encoursArticleStatutRésidentiel = encoursArticleStatutRésidentiel;
    }

    public Date getClotureArticleStatutRésidentiel() {
        return clotureArticleStatutRésidentiel;
    }

    public void setClotureArticleStatutRésidentiel(Date clotureArticleStatutRésidentiel) {
        this.clotureArticleStatutRésidentiel = clotureArticleStatutRésidentiel;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.articleStatutResidentielPK);
        hash = 79 * hash + Objects.hashCode(this.article);
        hash = 79 * hash + Objects.hashCode(this.statutResidentiel);
        hash = 79 * hash + Objects.hashCode(this.ouvertureVocation);
        hash = 79 * hash + (this.encoursArticleStatutRésidentiel ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.clotureArticleStatutRésidentiel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArticleStatutRésidentiel other = (ArticleStatutRésidentiel) obj;
        if (!Objects.equals(this.articleStatutResidentielPK, other.articleStatutResidentielPK)) {
            return false;
        }
        return true;
    }

   
}
