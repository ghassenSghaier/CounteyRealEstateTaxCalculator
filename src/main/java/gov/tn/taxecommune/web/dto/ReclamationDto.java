package gov.tn.taxecommune.web.dto;

/**
 * Created by Keno&Kemo on 22.10.2017..
 */
public class ReclamationDto {

	private Long id;

	private String article;

	private String taxation;

	private String motifReclamation;

	public ReclamationDto(Long id, String article, String taxation, String motifReclamation) {
		super();
		this.id = id;
		this.article = article;
		this.taxation = taxation;
		this.motifReclamation = motifReclamation;
	}

	public ReclamationDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
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

}
