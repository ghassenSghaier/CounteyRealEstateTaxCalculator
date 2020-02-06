package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Vocation")
public class Vocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "code_vocation", unique = true)
    private String codeVocation;

    @Column(name = "nom_vocation", unique = true)
    private String nomVocation;

    public Vocation(String codeVocation, String nomVocation) {
        super();
        this.codeVocation = codeVocation;
        this.nomVocation = nomVocation;
    }

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vocation", fetch = FetchType.EAGER)
    private Collection<Categorie> categories = new ArrayList<>();

    public Vocation() {
    }

    public String getCodeVocation() {
        return codeVocation;
    }

    public void setCodeVocation(String codeVocation) {
        this.codeVocation = codeVocation;
    }

    public String getNomVocation() {
        return nomVocation;
    }

    public void setNomVocation(String nomVocation) {
        this.nomVocation = nomVocation;
    }

    public Collection<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Categorie> categories) {
        this.categories = categories;
    }

}
