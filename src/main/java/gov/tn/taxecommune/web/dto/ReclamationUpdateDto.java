package gov.tn.taxecommune.web.dto;

import gov.tn.taxecommune.entity.ArticleTaxation;
import gov.tn.taxecommune.service.ArticleTaxationService;

/**
 * Created by Keno&Kemo on 03.12.2017..
 */
public class ReclamationUpdateDto {

	private Long id;

	private String taxation;

	private String motifReclamation;

	private ArticleTaxation articleTaxation;

	private ArticleTaxationService articleTaxationService;

	public ReclamationUpdateDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaxation() {
		return taxation;
	}

	public void setTaxation(String taxation) {
		this.taxation = taxation;
	}

	public String getMotifReclamation() {
		return motifReclamation;
	}

	public void setMotifReclamation(String motifReclamation) {
		this.motifReclamation = motifReclamation;
	}

	public ArticleTaxation getArticleTaxation() {
		return articleTaxation;
	}

	public void setArticleTaxation(ArticleTaxation articleTaxation) {
		this.articleTaxation = articleTaxation;
	}

	public ArticleTaxationService getArticleTaxationService() {
		return articleTaxationService;
	}

	public void setArticleTaxationService(ArticleTaxationService articleTaxationService) {
		this.articleTaxationService = articleTaxationService;
	}

}