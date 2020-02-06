package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArticlePrestationPK implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Column(name = "num_municipal")
    private String codeArticle;

    @Column(name = "code_prestation")
    private String codePrestation;

    public ArticlePrestationPK() {
    }

    public ArticlePrestationPK(String codeArticle, String codePrestation) {
        this.codeArticle = codeArticle;
        this.codePrestation = codePrestation;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(String codePrestation) {
        this.codePrestation = codePrestation;
    }

}
