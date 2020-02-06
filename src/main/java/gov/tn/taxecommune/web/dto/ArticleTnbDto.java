package gov.tn.taxecommune.web.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import gov.tn.taxecommune.entity.ArticlePrestation;
import gov.tn.taxecommune.entity.CategorieArticle;

/**
 * Created by Keno&Kemo on 22.10.2017..
 */
public class ArticleTnbDto extends ArticleDto {

	@NotNull(message = "densité Article is required")
	private String codedensité;

	public ArticleTnbDto(Long id, @NotNull(message = "numéro municipal is required") String numeroMunicipal,
			@NotBlank(message = "code postal is required") String codePostal,
			@NotNull(message = "surface total is required") double surfaceTotal,
			@NotNull(message = "prestation is required") List<String> pres,
			@NotBlank(message = "nom Propriétaire is required") String nomProp,
			@NotBlank(message = "prenom Propriétaire is required") String prenomProp,
			@NotBlank(message = "nom Entreprise Propriétaire is required") String nomEntre,
			@NotNull(message = "Identifiant  is required") String propIdentifiant,
			@NotNull(message = "type propriétaire  is required") String typeProp,
			@NotNull(message = "numero de telephone  is required") String numTel,
			@Email @NotNull(message = "email  is required") String email,
			@NotNull(message = "adresse  is required") String adresse,
			@NotNull(message = "codePostal  is required") String codePostalProp,
			@NotNull(message = "ville  is required") String ville,
			@NotBlank(message = "nom non Propriétaire is required") String nonPropNom,
			@NotBlank(message = "prenom Propriétaire is required") String nonPropPrenom,
			@NotBlank(message = "nom Entreprise non Propriétaire is required") String nonPropEntre,
			@NotNull(message = "Identifiant non propriétaire  is required") String nonPropIds,
			List<ArticlePrestation> prestations, List<CategorieArticle> cvs,
			@NotNull(message = "rue is required") String codeRue, String cin, String codeFiscal, String numPasseport,
			String carteSejour, @NotNull(message = "densité Article is required") String codedensité) {
		super(id, numeroMunicipal, codePostal, surfaceTotal, pres, nomProp, prenomProp, nomEntre, propIdentifiant,
				typeProp, numTel, email, adresse, codePostalProp, ville, nonPropNom, nonPropPrenom, nonPropEntre,
				nonPropIds, prestations, cvs, codeRue, cin, codeFiscal, numPasseport, carteSejour);
		this.codedensité = codedensité;
	}

	public ArticleTnbDto() {
		super();
	}

	public String getCodedensité() {
		return codedensité;
	}

	public void setCodedensité(String codedensité) {
		this.codedensité = codedensité;
	}

}
