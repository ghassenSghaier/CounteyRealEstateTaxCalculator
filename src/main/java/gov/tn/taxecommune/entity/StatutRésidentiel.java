package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "statut_residentiel")
public class StatutRésidentiel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "code_statut", unique = true)
    private String codeStatut;

    @Column(name = "nom_statut", unique = true)
    private String nomStatut;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statutResidentiel", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<ArticleStatutRésidentiel> articlestatutRésidence;

    public StatutRésidentiel(String codeStatut, String nomStatut) {
        super();
        this.codeStatut = codeStatut;
        this.nomStatut = nomStatut;
    }

    public StatutRésidentiel(String codeStatut, String nomStatut,
            List<ArticleStatutRésidentiel> articlestatutRésidence) {
        super();
        this.codeStatut = codeStatut;
        this.nomStatut = nomStatut;
        this.articlestatutRésidence = articlestatutRésidence;
    }

    public StatutRésidentiel() {
        super();
    }

    public String getCodeStatut() {
        return codeStatut;
    }

    public void setCodeStatut(String codeStatut) {
        this.codeStatut = codeStatut;
    }

    public String getNomStatut() {
        return nomStatut;
    }

    public void setNomStatut(String nomStatut) {
        this.nomStatut = nomStatut;
    }

    public Collection<ArticleStatutRésidentiel> getArticlestatutRésidence() {
        return articlestatutRésidence;
    }

    public void setArticlestatutRésidence(Collection<ArticleStatutRésidentiel> articlestatutRésidence) {
        this.articlestatutRésidence = articlestatutRésidence;
    }

}
