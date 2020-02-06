package gov.tn.taxecommune.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="reclamation")
public class Reclamation extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "code_reclamation", length = 6, unique = true)
	private String codeReclamation;

	@OneToOne(mappedBy = "reclamation")
	private ArticleTaxation articleTaxation;

	private String motifReclamation;

	private boolean enCours;

	private Date dateReclamation;

	public Reclamation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeReclamation() {
		return codeReclamation;
	}

	public void setCodeReclamation(String codeReclamation) {
		this.codeReclamation = codeReclamation;
	}

	public ArticleTaxation getArticleTaxation() {
		return articleTaxation;
	}

	public void setArticleTaxation(ArticleTaxation articleTaxation) {
		this.articleTaxation = articleTaxation;
	}

	public String getMotifReclamation() {
		return motifReclamation;
	}

	public void setMotifReclamation(String motifReclamation) {
		this.motifReclamation = motifReclamation;
	}

	public boolean isEnCours() {
		return enCours;
	}

	public void setEnCours(boolean enCours) {
		this.enCours = enCours;
	}

	public Date getDateReclamation() {
		return dateReclamation;
	}

	public void setDateReclamation(Date dateReclamation) {
		this.dateReclamation = dateReclamation;
	}

}
