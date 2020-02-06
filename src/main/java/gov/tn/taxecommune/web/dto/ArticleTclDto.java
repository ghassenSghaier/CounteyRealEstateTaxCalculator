package gov.tn.taxecommune.web.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import gov.tn.taxecommune.entity.ArticlePrestation;
import gov.tn.taxecommune.entity.ArticleTypeActivité;
import gov.tn.taxecommune.entity.CategorieArticle;

/**
 * Created by Keno&Kemo on 22.10.2017..
 */
public class ArticleTclDto extends ArticleDto {

	@NotNull(message = "surface couvert is required")
	private double surfaceCouvert;

	@NotBlank(message = "activité principale is required")
	private String activitePrincipal;

	/** activité secondaire de l'article **/
	@NotBlank(message = "activité secondaire is required")
	private String activiteSecondaire;

	@NotBlank(message = "com commercial is required")
	private String nomCommercial;

	// code activité
	@NotBlank(message = "type activité is required")
	private String typeActivite;

	private List<ArticleTypeActivité> articleActivite;

	@NotBlank(message = "marque publicitaire is required")
	private String marquePublicitaire;

	@NotBlank(message = "travaux publics is required")
	private String travauxPublics;

	public ArticleTclDto(Long id, @NotNull(message = "numéro municipal is required") String numeroMunicipal,
			@NotBlank(message = "code postal is required") String codePostal,
			@NotNull(message = "surface total is required") double surfaceTotal,
			@NotNull(message = "prestation is required") List<String> pres,
//			@NotBlank(message = "type occupant is required") String choixCont,
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
			String carteSejour, @NotNull(message = "surface couvert is required") double surfaceCouvert,
			@NotBlank(message = "activité principale is required") String activitePrincipal,
			@NotBlank(message = "activité secondaire is required") String activiteSecondaire,
			@NotBlank(message = "com commercial is required") String nomCommercial,
			@NotBlank(message = "type activité is required") String typeActivite,
			List<ArticleTypeActivité> articleActivite,
			@NotBlank(message = "marque publicitaire is required") String marquePublicitaire,
			@NotBlank(message = "travaux publics is required") String travauxPublics) {
		super(id, numeroMunicipal, codePostal, surfaceTotal, pres,  nomProp, prenomProp, nomEntre,
				propIdentifiant, typeProp, numTel, email, adresse, codePostalProp, ville, nonPropNom, nonPropPrenom,
				nonPropEntre, nonPropIds, prestations, cvs, codeRue, cin, codeFiscal, numPasseport, carteSejour);
		this.surfaceCouvert = surfaceCouvert;
		this.activitePrincipal = activitePrincipal;
		this.activiteSecondaire = activiteSecondaire;
		this.nomCommercial = nomCommercial;
		this.typeActivite = typeActivite;
		this.articleActivite = articleActivite;
		this.marquePublicitaire = marquePublicitaire;
		this.travauxPublics = travauxPublics;
	}

	public ArticleTclDto() {
		super();
	}

	public double getSurfaceCouvert() {
		return surfaceCouvert;
	}

	public void setSurfaceCouvert(double surfaceCouvert) {
		this.surfaceCouvert = surfaceCouvert;
	}

	public String getNomCommercial() {
		return nomCommercial;
	}

	public void setNomCommercial(String nomCommercial) {
		this.nomCommercial = nomCommercial;
	}

	public String getActivitePrincipal() {
		return activitePrincipal;
	}

	public void setActivitePrincipal(String activitePrincipal) {
		this.activitePrincipal = activitePrincipal;
	}

	public String getActiviteSecondaire() {
		return activiteSecondaire;
	}

	public void setActiviteSecondaire(String activiteSecondaire) {
		this.activiteSecondaire = activiteSecondaire;
	}

	public String getTypeActivite() {
		return typeActivite;
	}

	public void setTypeActivite(String typeActivite) {
		this.typeActivite = typeActivite;
	}

	public String getMarquePublicitaire() {
		return marquePublicitaire;
	}

	public void setMarquePublicitaire(String marquePublicitaire) {
		this.marquePublicitaire = marquePublicitaire;
	}

	public String getTravauxPublics() {
		return travauxPublics;
	}

	public void setTravauxPublics(String travauxPublics) {
		this.travauxPublics = travauxPublics;
	}

	public List<ArticleTypeActivité> getArticleActivite() {
		return articleActivite;
	}

	public void setArticleActivite(List<ArticleTypeActivité> articleActivite) {
		this.articleActivite = articleActivite;
	}
}
