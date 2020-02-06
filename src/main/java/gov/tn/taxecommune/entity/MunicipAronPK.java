package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MunicipAronPK implements Serializable {

    @Column(name = "code_municipalite")
    private String codeMunicip;

    @Column(name = "code_arrondissement")
    private String codeAron;

    public MunicipAronPK() {
    }

    public MunicipAronPK(String codeAron, String codeMunicip) {
        this.codeAron = codeAron;
        this.codeMunicip = codeMunicip;
    }

    public String getCodeMunicip() {
        return codeMunicip;
    }

    public void setCodeMunicip(String codeMunicip) {
        this.codeMunicip = codeMunicip;
    }

    public String getCodeAron() {
        return codeAron;
    }

    public void setCodeAron(String codeAron) {
        this.codeAron = codeAron;
    }

    
}
