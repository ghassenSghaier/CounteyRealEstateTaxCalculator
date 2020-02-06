package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArticleRuePK implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Column(name = "code_rue")
    private String codeRue;

    @Column(name = "num_municipal")
    private String numMunicipal;

    public ArticleRuePK() {
    }

    public ArticleRuePK(String codeRue, String numMunicipal) {
        this.codeRue = codeRue;
        this.numMunicipal = numMunicipal;
    }

    public String getCodeRue() {
        return codeRue;
    }

    public void setCodeRue(String codeRue) {
        this.codeRue = codeRue;
    }

    public String getNumMunicipal() {
        return numMunicipal;
    }

    public void setNumMunicipal(String numMunicipal) {
        this.numMunicipal = numMunicipal;
    }

    
}
