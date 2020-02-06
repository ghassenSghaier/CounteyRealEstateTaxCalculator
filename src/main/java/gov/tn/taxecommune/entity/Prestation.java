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
@Table(name = "prestation")
public class Prestation implements Serializable {

    private static final long serialVersionUID = 1L;   

    @Id
    @NotNull
    @Column(name = "code_prestation", length = 4, unique = true)
    private String codePrestation;

    @Column(name = "nom_prestation", unique = true)
    private String nomPrestation;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prestation", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ArticlePrestation> articlePrestation;

    public Prestation(String codePrestation, String nomPrestation, List<ArticlePrestation> articlePrestation) {
        super();
        this.codePrestation = codePrestation;
        this.nomPrestation = nomPrestation;
        this.articlePrestation = articlePrestation;
    }

    public Prestation(String codePrestation, String nomPrestation) {
        super();
        this.codePrestation = codePrestation;
        this.nomPrestation = nomPrestation;
    }

    public Prestation() {
        super();
    }
   

    public String getCodePrestation() {
        return codePrestation;
    }

    public void setCodePrestation(String codePrestation) {
        this.codePrestation = codePrestation;
    }

    public String getNomPrestation() {
        return nomPrestation;
    }

    public void setNomPrestation(String nomPrestation) {
        this.nomPrestation = nomPrestation;
    }

    public List<ArticlePrestation> getArticlePrestation() {
        return articlePrestation;
    }

    public void setArticlePrestation(List<ArticlePrestation> articlePrestation) {
        this.articlePrestation = articlePrestation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codePrestation == null) ? 0 : codePrestation.hashCode());
        result = prime * result + ((nomPrestation == null) ? 0 : nomPrestation.hashCode());
        return result;
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
        Prestation other = (Prestation) obj;
        if (codePrestation == null) {
            if (other.codePrestation != null) {
                return false;
            }
        } else if (!codePrestation.equals(other.codePrestation)) {
            return false;
        }
        if (nomPrestation == null) {
            if (other.nomPrestation != null) {
                return false;
            }
        } else if (!nomPrestation.equals(other.nomPrestation)) {
            return false;
        }
        return true;
    }

}
