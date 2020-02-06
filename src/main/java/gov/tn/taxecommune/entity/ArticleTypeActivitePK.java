package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArticleTypeActivitePK implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Column(name = "num_municipal")
    private String codeArticle;

    @Column(name = "code_type")
    private String codeType;

    public ArticleTypeActivitePK() {
    }

    public ArticleTypeActivitePK(String codeArticle, String codeType) {
        this.codeArticle = codeArticle;
        this.codeType = codeType;
    }

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

}
