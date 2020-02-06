package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Localité implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "num", length = 2)
	private int numLocalité;

	@Column(name = "nom", length = 2)
	private String nomLocalité;

	@ManyToOne
	private Gouvernorat gouvernorat;

	public Localité(String nomLocalité, int numLocalité) {
		super();
		this.nomLocalité = nomLocalité;
		this.numLocalité = numLocalité;
	}

	public Localité() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getnumLocalité() {
		return numLocalité;
	}

	public void setnumLocalité(int numLocalité) {
		this.numLocalité = numLocalité;
	}

	public String getNomLocalité() {
		return nomLocalité;
	}

	public void setNomLocalité(String nomLocalité) {
		this.nomLocalité = nomLocalité;
	}
}
