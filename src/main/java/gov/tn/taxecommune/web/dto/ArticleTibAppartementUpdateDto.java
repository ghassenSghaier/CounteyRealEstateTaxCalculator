package gov.tn.taxecommune.web.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import gov.tn.taxecommune.customAnnotations.ValidDoubleNumericField;
import gov.tn.taxecommune.entity.Rue;

public class ArticleTibAppartementUpdateDto extends ArticleTibUpdateDto {

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

	public ArticleTibAppartementUpdateDto(@NotNull(message = "numéro municipal is required") String numeroMunicipal,
			@NotBlank(message = "code postal is required") String codePostal,
			@NotNull(message = "surface total is required") double surfaceTotal,
			@NotNull(message = "prestation is required") List<String> prestations,
			@NotNull(message = "rue is required") Rue codeRue,
			@NotNull(message = "surface Couvert is required") double surfaceCouvert,
			@NotNull(message = "article répétée is required") int repArticle,
			@NotBlank(message = "statut résidentiel is required") String statutRésidence,
			@NotBlank(message = "type article is required") String typeArticle,
			@NotBlank(message = "Résidence immeuble is required") String résidenceImmeuble,
			@NotBlank(message = "Nom immeuble is required") String nomImmeuble,
			@NotBlank(message = "Numéro d'étage immeuble is required") int numeroEtage,
			@NotBlank(message = "Numéro d'appartement immeuble is required") int numeroAppartement) {
		super(numeroMunicipal, codePostal, surfaceTotal, prestations, codeRue, surfaceCouvert, repArticle,
				statutRésidence, typeArticle);
		this.résidenceImmeuble = résidenceImmeuble;
		this.nomImmeuble = nomImmeuble;
		this.numeroEtage = numeroEtage;
		this.numeroAppartement = numeroAppartement;
	}

	public ArticleTibAppartementUpdateDto() {
		super();
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
