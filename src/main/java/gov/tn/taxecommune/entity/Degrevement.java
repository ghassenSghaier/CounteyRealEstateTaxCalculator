package gov.tn.taxecommune.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="degrevement")
public class Degrevement extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "code_degrevement", length = 6, unique = true)
	private String codeDegrevement;

	@OneToOne
	private ArticleTaxation articleTaxation;

	private String typeDegrevement;
	
	private String statutDegrevement;

	private String decisionDegrevement;

	private double montantDegrevement;

	public Degrevement() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeDegrevement() {
		return codeDegrevement;
	}

	public void setCodeDegrevement(String codeDegrevement) {
		this.codeDegrevement = codeDegrevement;
	}

	public ArticleTaxation getArticleTaxation() {
		return articleTaxation;
	}

	public void setArticleTaxation(ArticleTaxation articleTaxation) {
		this.articleTaxation = articleTaxation;
	}

	public String getTypeDegrevement() {
		return typeDegrevement;
	}

	public void setTypeDegrevement(String typeDegrevement) {
		this.typeDegrevement = typeDegrevement;
	}

	public String getDecisionDegrevement() {
		return decisionDegrevement;
	}

	public void setDecisionDegrevement(String decisionDegrevement) {
		this.decisionDegrevement = decisionDegrevement;
	}

	public double getMontantDegrevement() {
		return montantDegrevement;
	}

	public void setMontantDegrevement(double montantDegrèvement) {
		this.montantDegrevement = montantDegrèvement;
	}

	public String getStatutDegrevement() {
		return statutDegrevement;
	}

	public void setStatutDegrevement(String statutDegrevement) {
		this.statutDegrevement = statutDegrevement;
	}

}
