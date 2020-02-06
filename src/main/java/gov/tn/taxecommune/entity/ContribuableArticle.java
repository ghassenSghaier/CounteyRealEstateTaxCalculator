package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "contribubale_article")
public class ContribuableArticle implements Serializable {

    private static final long serialVersionUID = 1L;    

    @EmbeddedId
    private ContribuableArticlePK contribuableArticlePK;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeContribuable")
    @JoinColumn(name = "code_contribuable", referencedColumnName = "code_contribuable", insertable = false, updatable = false)
    private Contribuable contribuable;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId(value = "codeArticle")
    @JoinColumn(name = "num_municipal", referencedColumnName = "num_municipal", insertable = false, updatable = false)
    private Article article;

    private Date registeredAt;

    private Date closedAt;

    private boolean enCours;

    @Column(name = "caractère_ropriété", columnDefinition = "ENUM('Héritier','Personnel','Associé')")
    private CaractèrePropriété caractèrePropriété;

    @Column(name = "type_occupation", columnDefinition = "ENUM('propriétaire','NonPropriétaire')")
    private TypeOccupant typeOccupant;

    @Column(name = "statut_résidence_article", columnDefinition = "ENUM('principal','secondaire')")
    private String statutRésidence;

    public ContribuableArticle(Contribuable contribuable, Article article, Date registeredAt,
            CaractèrePropriété caractèrePropriété, TypeOccupant typeOccupant) {
        super();
        this.contribuable = contribuable;
        this.article = article;
        this.registeredAt = registeredAt;
        this.caractèrePropriété = caractèrePropriété;
        this.typeOccupant = typeOccupant;
    }

    public ContribuableArticle() {
        // TODO Auto-generated constructor stub
    }

    public Contribuable getContribuable() {
        return contribuable;
    }

    public void setContribuable(Contribuable contribuable) {
        this.contribuable = contribuable;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public CaractèrePropriété getCaractèrePropriété() {
        return caractèrePropriété;
    }

    public void setCaractèrePropriété(CaractèrePropriété caractèrePropriété) {
        this.caractèrePropriété = caractèrePropriété;
    }

    public TypeOccupant getTypeOccupant() {
        return typeOccupant;
    }

    public void setTypeOccupant(TypeOccupant typeOccupant) {
        this.typeOccupant = typeOccupant;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public boolean isEnCours() {
        return enCours;
    }

    public void setEnCours(boolean enCours) {
        this.enCours = enCours;
    }

    public String getStatutRésidence() {
        return statutRésidence;
    }

    public void setStatutRésidence(String statutRésidence) {
        this.statutRésidence = statutRésidence;
    }
}
