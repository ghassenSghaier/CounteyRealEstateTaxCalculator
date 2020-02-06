package gov.tn.taxecommune.web.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import gov.tn.taxecommune.customAnnotations.ValidDoubleNumericField;
import gov.tn.taxecommune.entity.ArticlePrestation;
import gov.tn.taxecommune.entity.CategorieArticle;

public class ArticleTibAppartementDto extends ArticleTibDto {

	@NotBlank(message = "Résidence immeuble is required")
	private String résidenceImmeuble;

	@NotBlank(message = "Nom immeuble is required")
	private String nomImmeuble;

	@ValidDoubleNumericField
	@NotBlank(message = "Numéro d'étage immeuble is required")
	private int numeroEtage;

	@ValidDoubleNumericField
	@NotBlank(message = "Numéro d'appartement immeuble is required")
	private int numeroAppartement;

	public ArticleTibAppartementDto(Long id, @NotNull(message = "numéro municipal is required") String numeroMunicipal,
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
			String carteSejour, @NotNull(message = "surface Couvert is required") double surfaceCouvert,
			@NotNull(message = "article répétée is required") int repArticle,
			@NotBlank(message = "statut résidentiel is required") String statutRésidence,
			@NotBlank(message = "type article is required") String typeArticle,
			@NotBlank(message = "Résidence immeuble is required") String résidenceImmeuble,
			@NotBlank(message = "Nom immeuble is required") String nomImmeuble,
			@NotBlank(message = "Numéro d'étage immeuble is required") int numeroEtage,
			@NotBlank(message = "Numéro d'appartement immeuble is required") int numeroAppartement) {
		super(id, numeroMunicipal, codePostal, surfaceTotal, pres, nomProp, prenomProp, nomEntre, propIdentifiant,
				typeProp, numTel, email, adresse, codePostalProp, ville, nonPropNom, nonPropPrenom, nonPropEntre,
				nonPropIds, prestations, cvs, codeRue, cin, codeFiscal, numPasseport, carteSejour, surfaceCouvert,
				repArticle, statutRésidence, typeArticle);
		this.résidenceImmeuble = résidenceImmeuble;
		this.nomImmeuble = nomImmeuble;
		this.numeroEtage = numeroEtage;
		this.numeroAppartement = numeroAppartement;
	}

	public String getRésidenceImmeuble() {
		return résidenceImmeuble;
	}

	public void setRésidenceImmeuble(String résidenceImmeuble) {
		this.résidenceImmeuble = résidenceImmeuble;
	}

	public String getNomImmeuble() {
		return nomImmeuble;
	}

	public void setNomImmeuble(String nomImmeuble) {
		this.nomImmeuble = nomImmeuble;
	}

	public int getNumeroEtage() {
		return numeroEtage;
	}

	public void setNumeroEtage(int numeroEtage) {
		this.numeroEtage = numeroEtage;
	}

	public int getNumeroAppartement() {
		return numeroAppartement;
	}

	public void setNumeroAppartement(int numeroAppartement) {
		this.numeroAppartement = numeroAppartement;
	}

}
