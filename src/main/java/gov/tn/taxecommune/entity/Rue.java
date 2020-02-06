package gov.tn.taxecommune.entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Rue")
public class Rue implements Serializable {

    @Id
    @NotNull
    @Column(name = "code_rue", length = 6, unique = true)
    private String codeRue;

    @Column(name = "nom_rue", length = 40, unique = true)
    private String nomRue;

    @Enumerated(EnumType.STRING)
    private ClasseRue classeRue;

    @Column(name = "autre_classe_rue", length = 20)
    private String autreClasseRue;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rue",fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<CelluleRue> celluleRues;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rue",fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<ArticleRue> articleRues;

    public Rue() {
    }

    public Rue(String codeRue, String nomRue, ClasseRue classeRue, String autreClasseRue, Collection<CelluleRue> celluleRues, Collection<ArticleRue> articleRues) {
        this.codeRue = codeRue;
        this.nomRue = nomRue;
        this.classeRue = classeRue;
        this.autreClasseRue = autreClasseRue;
        this.celluleRues = celluleRues;
        this.articleRues = articleRues;
    }

    public Rue(String codeRue, String nomRue, ClasseRue classeRue, String autreClasseRue) {
        this.codeRue = codeRue;
        this.nomRue = nomRue;
        this.classeRue = classeRue;
        this.autreClasseRue = autreClasseRue;
    }

    
    
    

    public String getCodeRue() {
        return codeRue;
    }

    public void setCodeRue(String codeRue) {
        this.codeRue = codeRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public ClasseRue getClasseRue() {
        return classeRue;
    }

    public void setClasseRue(ClasseRue classeRue) {
        this.classeRue = classeRue;
    }

    public String getAutreClasseRue() {
        return autreClasseRue;
    }

    public void setAutreClasseRue(String autreClasseRue) {
        this.autreClasseRue = autreClasseRue;
    }

    public Collection<CelluleRue> getCelluleRues() {
        return celluleRues;
    }

    public void setCelluleRues(Collection<CelluleRue> celluleRues) {
        this.celluleRues = celluleRues;
    }

    public Collection<ArticleRue> getArticleRues() {
        return articleRues;
    }

    public void setArticleRues(Collection<ArticleRue> articleRues) {
        this.articleRues = articleRues;
    }
    
    
    
    

}
