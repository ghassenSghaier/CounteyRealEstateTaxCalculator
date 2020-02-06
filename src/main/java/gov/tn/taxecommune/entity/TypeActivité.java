package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "type_activite")
public class TypeActivité implements Serializable {

    private static final long serialVersionUID = 1L;   

    @Id
    @NotNull
    @Column(name = "code_type", unique = true)
    private String codeType;

    @Column(name = "nom_type", unique = true)
    private String nomType;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article",fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ArticleTypeActivité> articleActivite;

    public TypeActivité(String codeType, String nomType) {
        super();
        this.codeType = codeType;
        this.nomType = nomType;
    }

    public TypeActivité() {
        super();
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public List<ArticleTypeActivité> getArticleActivite() {
        return articleActivite;
    }

    public void setArticleActivite(List<ArticleTypeActivité> articleActivité) {
        this.articleActivite = articleActivité;
    }

}
