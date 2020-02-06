package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SecteurCellulePK implements Serializable {

    private static final long serialVersionUID = 1L;   

    @Column(name = "code_secteur")
    private String codeSecteur;

    @Column(name = "code_cellule")
    private String codeCellule;

    public SecteurCellulePK() {
    }

    public SecteurCellulePK(String codeSecteur, String codeCellule) {
        this.codeSecteur = codeSecteur;
        this.codeCellule = codeCellule;
    }

    public String getCodeSecteur() {
        return codeSecteur;
    }

    public void setCodeSecteur(String codeSecteur) {
        this.codeSecteur = codeSecteur;
    }

    public String getCodeCellule() {
        return codeCellule;
    }

    public void setCodeCellule(String codeCellule) {
        this.codeCellule = codeCellule;
    }
    
    

}
