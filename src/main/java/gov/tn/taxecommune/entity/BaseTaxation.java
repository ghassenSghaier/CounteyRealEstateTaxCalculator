package gov.tn.taxecommune.entity;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "base_taxation")
public class BaseTaxation implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Id
    @NotNull
    @Column(name = "code_base", unique = true)
    private String codeBase;

    @Column(name = "nom_base", unique = true)
    private String nomBase;

    @OneToMany(mappedBy = "baseTaxation")
    private Collection<Article> articles;

    public BaseTaxation() {
    }

    public BaseTaxation(String codeBase, String nomBase, Collection<Article> articles) {
        this.codeBase = codeBase;
        this.nomBase = nomBase;
        this.articles = articles;
    }

    public String getCodeBase() {
        return codeBase;
    }

    public void setCodeBase(String codeBase) {
        this.codeBase = codeBase;
    }

    public String getNomBase() {
        return nomBase;
    }

    public void setNomBase(String nomBase) {
        this.nomBase = nomBase;
    }

    public Collection<Article> getArticles() {
        return articles;
    }

    public void setArticles(Collection<Article> articles) {
        this.articles = articles;
    }

}
