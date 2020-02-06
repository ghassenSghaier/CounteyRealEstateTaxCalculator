package gov.tn.taxecommune.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="region")
public class Region implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "code_region", length = 2)
	private int numRegion;

	@Column(name = "nom_region", length = 2)
	private String nomRegion;

	@OneToMany(mappedBy = "region")
	Set<Gouvernorat> gouvernorats;

	public Region(String nomRegion, int numRegion) {
		super();
		this.nomRegion = nomRegion;
		this.numRegion = numRegion;
	}

	public Region() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumRegion() {
		return numRegion;
	}

	public void setNumRegion(int numRegion) {
		this.numRegion = numRegion;
	}

	public String getNomRegion() {
		return nomRegion;
	}

	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}

}
