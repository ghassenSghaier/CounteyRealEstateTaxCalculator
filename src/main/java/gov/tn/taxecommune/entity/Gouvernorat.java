package gov.tn.taxecommune.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Gouvernorat implements Serializable {

    private static final long serialVersionUID = 1L;   
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "code_gouvernorat", length = 2)
	private int numGouvernorat;

	@Column(name = "nom_gouvernorat", length = 2)
	private String nomGouvernorat;

	@ManyToOne
	private Region region;

	@OneToMany(mappedBy = "gouvernorat")
	private Set<Localité> localités;

	public Gouvernorat(String nomGouvernorat, int numGouvernorat) {
		super();
		this.nomGouvernorat = nomGouvernorat;
		this.numGouvernorat = numGouvernorat;
	}

	public Gouvernorat() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumGouvernorat() {
		return numGouvernorat;
	}

	public void setNumGouvernorat(int numGouvernorat) {
		this.numGouvernorat = numGouvernorat;
	}

	public String getNomGouvernorat() {
		return nomGouvernorat;
	}

	public void setNomGouvernorat(String nomGouvernorat) {
		this.nomGouvernorat = nomGouvernorat;
	}
}
