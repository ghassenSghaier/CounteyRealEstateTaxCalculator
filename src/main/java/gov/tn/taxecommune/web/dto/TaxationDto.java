package gov.tn.taxecommune.web.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import gov.tn.taxecommune.entity.Article;

/**
 * Created by Keno&Kemo on 22.10.2017..
 */
public class TaxationDto {

	private Long id;

	@NotBlank(message = "montant fnah is required")
	private double montantFNAH;

	@NotBlank(message = "montant tib is required")
	private double montantTIB;

	@NotBlank(message = "montant tcl is required")
	private double montantTCL;

	@NotBlank(message = "montant ttnb is required")
	private double montantTTNB;

	@NotBlank(message = "code Article is required")
	private long numeroMunicipal;

	private Article article;
	
	@NotBlank(message = "date is required")
	private Date anneeTaxation;

	public TaxationDto(Long id, @NotBlank(message = "montant fnah is required") double montantFNAH,
			@NotBlank(message = "montant tib is required") double montantTIB,
			@NotBlank(message = "montant tcl is required") double montantTCL,
			@NotBlank(message = "montant ttnb is required") double montantTTNB,
			@NotBlank(message = "code Article is required") long numeroMunicipal,
			@NotBlank(message = "date is required") Date anneeTaxation) {
		super();
		this.id = id;
		this.montantFNAH = montantFNAH;
		this.montantTIB = montantTIB;
		this.montantTCL = montantTCL;
		this.montantTTNB = montantTTNB;
		this.numeroMunicipal = numeroMunicipal;
		this.anneeTaxation = anneeTaxation;
	}

	public TaxationDto() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getMontantFNAH() {
		return montantFNAH;
	}

	public void setMontantFNAH(double montantFNAH) {
		this.montantFNAH = montantFNAH;
	}

	public double getMontantTIB() {
		return montantTIB;
	}

	public void setMontantTIB(double montantTIB) {
		this.montantTIB = montantTIB;
	}

	public double getMontantTCL() {
		return montantTCL;
	}

	public void setMontantTCL(double montantTCL) {
		this.montantTCL = montantTCL;
	}

	public double getMontantTTNB() {
		return montantTTNB;
	}

	public void setMontantTTNB(double montantTTNB) {
		this.montantTTNB = montantTTNB;
	}

	public long getNumeroMunicipal() {
		return numeroMunicipal;
	}

	public void setNumeroMunicipal(long numeroMunicipal) {
		this.numeroMunicipal = numeroMunicipal;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Date getAnneeTaxation() {
		return anneeTaxation;
	}

	public void setAnneeTaxation(Date anneeTaxation) {
		this.anneeTaxation = anneeTaxation;
	}

}
