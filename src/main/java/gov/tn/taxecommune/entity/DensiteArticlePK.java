package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DensiteArticlePK implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Column(name = "code_densite")
    private String codeDensite;

    @Column(name = "num_municipal")
    private String codeArticle;

    public DensiteArticlePK() {
    }

    public DensiteArticlePK(String codeDensite, String codeArticle) {
        this.codeDensite = codeDensite;
        this.codeArticle = codeArticle;
    }

    public String getCodeDensite() {
        return codeDensite;
    }

    public void setCodeDensite(String codeDensite) {
        this.codeDensite = codeDensite;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    
    
    
    
}
