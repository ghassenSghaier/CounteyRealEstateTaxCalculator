package gov.tn.taxecommune.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
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
@Table(name = "tarification_prestation")
public class Tarificationprestation implements Serializable {

    private static final long serialVersionUID = 1L;   

    @Id
    @NotNull
    @Column(name = "code_tarification", unique = true)
    private String codeTarification;

    @Column(name = "taux_tarification", unique = true)
    private double tauxPrestation;

    @Column(name = "nombre_prestation_min", unique = true)
    private int nbMinPrestation;

    @Column(name = "nombre_prestation_max", unique = true)
    private int nbMaxPrestation;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tPrestation", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CategorieTarificationprestation> ctps;

    public Tarificationprestation(String codeTarification, double tauxPrestation, int nbMinPrestation,
            int nbMaxPrestation) {
        super();
        this.codeTarification = codeTarification;
        this.tauxPrestation = tauxPrestation;
        this.nbMinPrestation = nbMinPrestation;
        this.nbMaxPrestation = nbMaxPrestation;
    }

    public Tarificationprestation(String codeTarification, double tauxPrestation, int nbMinPrestation,
            int nbMaxPrestation, List<CategorieTarificationprestation> ctps) {
        super();
        this.codeTarification = codeTarification;
        this.tauxPrestation = tauxPrestation;
        this.nbMinPrestation = nbMinPrestation;
        this.nbMaxPrestation = nbMaxPrestation;
        this.ctps = ctps;
    }

    public Tarificationprestation() {
        super();
    }

    public String getCodeTarification() {
        return codeTarification;
    }

    public void setCodeTarification(String codeTarification) {
        this.codeTarification = codeTarification;
    }

    public double getTauxPrestation() {
        return tauxPrestation;
    }

    public void setTauxPrestation(double tauxPrestation) {
        this.tauxPrestation = tauxPrestation;
    }

    public int getNbMinPrestation() {
        return nbMinPrestation;
    }

    public void setNbMinPrestation(int nbMinPrestation) {
        this.nbMinPrestation = nbMinPrestation;
    }

    public int getNbMaxPrestation() {
        return nbMaxPrestation;
    }

    public void setNbMaxPrestation(int nbMaxPrestation) {
        this.nbMaxPrestation = nbMaxPrestation;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codeTarification == null) ? 0 : codeTarification.hashCode());
        result = prime * result + nbMaxPrestation;
        result = prime * result + nbMinPrestation;
        long temp;
        temp = Double.doubleToLongBits(tauxPrestation);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Tarificationprestation other = (Tarificationprestation) obj;
        if (codeTarification == null) {
            if (other.codeTarification != null) {
                return false;
            }
        } else if (!codeTarification.equals(other.codeTarification)) {
            return false;
        }
        if (nbMaxPrestation != other.nbMaxPrestation) {
            return false;
        }
        if (nbMinPrestation != other.nbMinPrestation) {
            return false;
        }
        if (Double.doubleToLongBits(tauxPrestation) != Double.doubleToLongBits(other.tauxPrestation)) {
            return false;
        }
        return true;
    }

}
