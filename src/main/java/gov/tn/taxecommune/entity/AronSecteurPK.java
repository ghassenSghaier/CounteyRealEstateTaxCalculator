package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AronSecteurPK implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Column(name = "code_arrondissement")
    private String codeAron;

    @Column(name = "code_secteur")
    private String codeSecteur;

    public AronSecteurPK() {
    }

    public AronSecteurPK(String codeAron, String codeSecteur) {
        this.codeAron = codeAron;
        this.codeSecteur = codeSecteur;
    }

    public String getCodeAron() {
        return codeAron;
    }

    public void setCodeAron(String codeAron) {
        this.codeAron = codeAron;
    }

    public String getCodeSecteur() {
        return codeSecteur;
    }

    public void setCodeSecteur(String codeSecteur) {
        this.codeSecteur = codeSecteur;
    }
    
    
    
}
