package gov.tn.taxecommune.web.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import gov.tn.taxecommune.entity.Rue;

/**
 * Created by Keno&Kemo on 22.10.2017..
 */
public class ArticleTibUpdateDto extends ArticleUpdateDto {

	@NotNull(message = "surface Couvert is required")
	private double surfaceCouvert;

	@NotNull(message = "article répétée is required")
	private int repArticle;

	@NotBlank(message = "statut résidentiel is required")
	private String statutRésidence;

	@NotBlank(message = "type article is required")
	private String typeArticle;

	public ArticleTibUpdateDto(@NotNull(message = "numéro municipal is required") long numeroMunicipal,
			@NotBlank(message = "code postal is required") String codePostal,
			@NotNull(message = "surface total is required") double surfaceTotal,
			@NotNull(message = "prestation is required") List<String> prestations,
			@NotNull(message = "rue is required") Rue codeRue,
			@NotNull(message = "surface Couvert is required") double surfaceCouvert,
			@NotNull(message = "article répétée is required") int repArticle,
			@NotBlank(message = "statut résidentiel is required") String statutRésidence,
			@NotBlank(message = "type article is required") String typeArticle) {
		super(numeroMunicipal, codePostal, surfaceTotal, prestations, codeRue);
		this.surfaceCouvert = surfaceCouvert;
		this.repArticle = repArticle;
		this.statutRésidence = statutRésidence;
		this.typeArticle = typeArticle;
	}

	public ArticleTibUpdateDto() {
		super();
	}

	public double getSurfaceCouvert() {
		return surfaceCouvert;
	}

	public void setSurfaceCouvert(double surfaceCouvert) {
		this.surfaceCouvert = surfaceCouvert;
	}

	public int getRepArticle() {
		return repArticle;
	}

	public void setRepArticle(int repArticle) {
		this.repArticle = repArticle;
	}

	public String getStatutRésidence() {
		return statutRésidence;
	}

	public void setStatutRésidence(String statutRésidence) {
		this.statutRésidence = statutRésidence;
	}

	public String getTypeArticle() {
		return typeArticle;
	}

	public void setTypeArticle(String typeArticle) {
		this.typeArticle = typeArticle;
	}

}
