package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CategorieTarificationprestationPK implements Serializable {

    private static final long serialVersionUID = 1L;    

    @Column(name = "code_tarification")
    private String codeTarification;

    @Column(name = "code_categorie")
    private String codeCategorie;

    public CategorieTarificationprestationPK() {
    }

    public CategorieTarificationprestationPK(String codeTarification, String codeCategorie) {
        this.codeTarification = codeTarification;
        this.codeCategorie = codeCategorie;
    }

    public String getCodeTarification() {
        return codeTarification;
    }

    public void setCodeTarification(String codeTarification) {
        this.codeTarification = codeTarification;
    }

    public String getCodeCategorie() {
        return codeCategorie;
    }

    public void setCodeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

}
