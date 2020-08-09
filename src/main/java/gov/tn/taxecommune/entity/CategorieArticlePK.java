package gov.tn.taxecommune.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CategorieArticlePK implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Column(name = "num_municipal")
    private String codeArticle;

    @Column(name = "code_categorie")
    private String codeCategorie;

    public CategorieArticlePK() {
    }

    public CategorieArticlePK(String codeArticle, String codeCategorie) {
        this.codeArticle = codeArticle;
        this.codeCategorie = codeCategorie;
    }

    

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    
    
}
