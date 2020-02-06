package gov.tn.taxecommune.web.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import gov.tn.taxecommune.customAnnotations.ValidCodePostal;
import gov.tn.taxecommune.entity.Rue;

/**
 * Created by Keno&Kemo on 03.12.2017..
 */
public class ArticleUpdateDto {

//	@ValidTripleNumericField
	@NotNull(message = "numéro municipal is required")
	private long numeroMunicipal;

	@ValidCodePostal
	@NotBlank(message = "code postal is required")
	private String codePostal;

	@NotNull(message = "surface total is required")
	private double surfaceTotal;

	@NotNull(message = "prestation is required")
	private List<String> prestations;

	@NotNull(message = "rue is required")
	private Rue rue;

	public ArticleUpdateDto(@NotNull(message = "numéro municipal is required") long numeroMunicipal,
			@NotBlank(message = "code postal is required") String codePostal,
			@NotNull(message = "surface total is required") double surfaceTotal,
			@NotNull(message = "prestation is required") List<String> prestations,
			@NotNull(message = "rue is required") Rue codeRue) {
		super();
		this.numeroMunicipal = numeroMunicipal;
		this.codePostal = codePostal;
		this.surfaceTotal = surfaceTotal;
		this.prestations = prestations;
		this.rue = codeRue;
	}

	public ArticleUpdateDto() {
		super();
	}

	public long getNumeroMunicipal() {
		return numeroMunicipal;
	}

	public void setNumeroMunicipal(long numeroMunicipal) {
		this.numeroMunicipal = numeroMunicipal;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public double getSurfaceTotal() {
		return surfaceTotal;
	}

	public void setSurfaceTotal(double surfaceTotal) {
		this.surfaceTotal = surfaceTotal;
	}

	public List<String> getPrestations() {
		return prestations;
	}

	public void setPrestations(List<String> prestations) {
		this.prestations = prestations;
	}

	public Rue getRue() {
		return rue;
	}

	public void setRue(Rue rue) {
		this.rue = rue;
	}

}