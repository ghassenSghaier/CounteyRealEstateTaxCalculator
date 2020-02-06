package gov.tn.taxecommune.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parametre")
public class Parametre implements Serializable {

    private static final long serialVersionUID = 1L;   

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "code_param", unique = true)
	private String codeParam;

	@Column(name = "nom_param", unique = true)
	private String nomParam;

	@Column(name = "valeur_param", unique = true)
	private double valeurParam;
	

	public Parametre() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeParam() {
		return codeParam;
	}

	public void setCodeParam(String codeParam) {
		this.codeParam = codeParam;
	}

	public String getNomParam() {
		return nomParam;
	}

	public void setNomParam(String nomParam) {
		this.nomParam = nomParam;
	}

	public double getValeurParam() {
		return valeurParam;
	}

	public void setValeurParam(double valeurParam) {
		this.valeurParam = valeurParam;
	}
	
	


}
