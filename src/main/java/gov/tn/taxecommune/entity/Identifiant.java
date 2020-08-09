package gov.tn.taxecommune.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Identifiant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cin", length = 8)
    private String cin;

    @Column(name = "cod_fis", length = 12)
    private String cod_fis;

    @Column(name = "num_pas", length = 12)
    private String num_pas;

    @Column(name = "cart_sejour", length = 9)
    private String cart_sejour;

    @OneToOne
    private Contribuable contribuable;

    public Identifiant(String cin, String cod_fis, String num_pas, String cart_sejour) {
        super();
        this.cin = cin;
        this.cod_fis = cod_fis;
        this.num_pas = num_pas;
        this.cart_sejour = cart_sejour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Contribuable getContribuable() {
        return contribuable;
    }

    public void setContribuable(Contribuable contribuable) {
        this.contribuable = contribuable;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getCod_fis() {
        return cod_fis;
    }

    public void setCod_fis(String cod_fis) {
        this.cod_fis = cod_fis;
    }

    public String getNum_pas() {
        return num_pas;
    }

    public void setNum_pas(String num_pas) {
        this.num_pas = num_pas;
    }

    public String getCart_sejour() {
        return cart_sejour;
    }

    public void setCart_sejour(String cart_sejour) {
        this.cart_sejour = cart_sejour;
    }

}
