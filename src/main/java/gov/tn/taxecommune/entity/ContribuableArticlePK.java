package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContribuableArticlePK implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Column(name = "code_contribuable")
    private String codeContribuable;

    @Column(name = "num_municipal")
    private String codeArticle;

    public ContribuableArticlePK() {
    }

    public ContribuableArticlePK(String codeContribuable, String codeArticle) {
        this.codeContribuable = codeContribuable;
        this.codeArticle = codeArticle;
    }

    public String getCodeContribuable() {
        return codeContribuable;
    }

    public void setCodeContribuable(String codeContribuable) {
        this.codeContribuable = codeContribuable;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

}
