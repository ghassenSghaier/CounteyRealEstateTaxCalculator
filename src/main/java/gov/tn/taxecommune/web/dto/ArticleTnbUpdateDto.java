package gov.tn.taxecommune.web.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import gov.tn.taxecommune.entity.Densite;
import gov.tn.taxecommune.entity.Rue;

/**
 * Created by Keno&Kemo on 03.12.2017..
 */
public class ArticleTnbUpdateDto extends ArticleUpdateDto {

	private Densite densité;

	public ArticleTnbUpdateDto(@NotNull(message = "numéro municipal is required") String numeroMunicipal,
			@NotBlank(message = "code postal is required") String codePostal,
			@NotNull(message = "surface total is required") double surfaceTotal,
			@NotNull(message = "prestation is required") List<String> prestations,
			@NotNull(message = "rue is required") Rue codeRue, Densite densité) {
		super(numeroMunicipal, codePostal, surfaceTotal, prestations, codeRue);
		this.densité = densité;
	}

	public ArticleTnbUpdateDto() {
		super();
	}

	public Densite getDensité() {
		return densité;
	}

	public void setDensité(Densite densité) {
		this.densité = densité;
	}

}