package gov.tn.taxecommune.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArticleStatutResidentielPK implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Column(name = "statut_residentiel")
    private String statutResidentiel;

    @Column(name = "num_municipal")
    private String numMunicipal;

    public ArticleStatutResidentielPK() {
    }

    public ArticleStatutResidentielPK(String statutResidentiel, String numMunicipal) {
        this.statutResidentiel = statutResidentiel;
        this.numMunicipal = numMunicipal;
    }

    public String getStatutResidentiel() {
        return statutResidentiel;
    }

    public void setStatutResidentiel(String statutResidentiel) {
        this.statutResidentiel = statutResidentiel;
    }

    public String getNumMunicipal() {
        return numMunicipal;
    }

    public void setNumMunicipal(String numMunicipal) {
        this.numMunicipal = numMunicipal;
    }

    

    

    
}
