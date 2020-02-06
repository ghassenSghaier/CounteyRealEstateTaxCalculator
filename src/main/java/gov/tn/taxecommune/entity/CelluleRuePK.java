package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CelluleRuePK implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Column(name = "code_cellule")
    private String codeCellule;

    @Column(name = "code_rue")
    private String codeRue;

    public CelluleRuePK() {
    }

    public CelluleRuePK(String codeCellule, String codeRue) {
        this.codeCellule = codeCellule;
        this.codeRue = codeRue;
    }

    public String getCodeCellule() {
        return codeCellule;
    }

    public void setCodeCellule(String codeCellule) {
        this.codeCellule = codeCellule;
    }

    public String getCodeRue() {
        return codeRue;
    }

    public void setCodeRue(String codeRue) {
        this.codeRue = codeRue;
    }

    
    
    
}
