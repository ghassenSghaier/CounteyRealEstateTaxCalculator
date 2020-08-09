package gov.tn.taxecommune.web.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import gov.tn.taxecommune.entity.Rue;

/**
 * Created by Keno&Kemo on 03.12.2017..
 */
public class ArticleTclUpdateDto extends ArticleUpdateDto {

	@NotNull(message = "surface couvert is required")
	private double surfaceCouvert;

	@NotBlank(message = "activité principale is required")
	private String activitéPrincipal;

	/** activité secondaire de l'article **/
	@NotBlank(message = "activité secondaire is required")
	private String activitéSecondaire;

	@NotBlank(message = "com commercial is required")
	private String nomCommercial;

	@NotBlank(message = "type activité is required")
	private String typeActivité;

	@NotBlank(message = "marque publicitaire is required")
	private boolean marquePublicitaire;

	@NotBlank(message = "travaux publics is required")
	private boolean travauxPublics;

	public ArticleTclUpdateDto(@NotNull(message = "numéro municipal is required") String numeroMunicipal,
			@NotBlank(message = "code postal is required") String codePostal,
			@NotNull(message = "surface total is required") double surfaceTotal,
			@NotNull(message = "prestation is required") List<String> prestations,
			@NotNull(message = "rue is required") Rue codeRue,
			@NotNull(message = "surface couvert is required") double surfaceCouvert,
			@NotBlank(message = "activité principale is required") String activitéPrincipal,
			@NotBlank(message = "activité secondaire is required") String activitéSecondaire,
			@NotBlank(message = "com commercial is required") String nomCommercial,
			@NotBlank(message = "type activité is required") String typeActivité,
			@NotBlank(message = "marque publicitaire is required") boolean marquePublicitaire,
			@NotBlank(message = "travaux publics is required") boolean travauxPublics) {
		super(numeroMunicipal, codePostal, surfaceTotal, prestations, codeRue);
		this.surfaceCouvert = surfaceCouvert;
		this.activitéPrincipal = activitéPrincipal;
		this.activitéSecondaire = activitéSecondaire;
		this.nomCommercial = nomCommercial;
		this.typeActivité = typeActivité;
		this.marquePublicitaire = marquePublicitaire;
		this.travauxPublics = travauxPublics;
	}

	public ArticleTclUpdateDto() {
		super();
	}

	public double getSurfaceCouvert() {
		return surfaceCouvert;
	}

	public void setSurfaceCouvert(double surfaceCouvert) {
		this.surfaceCouvert = surfaceCouvert;
	}

	public String getActivitéPrincipal() {
		return activitéPrincipal;
	}

	public void setActivitéPrincipal(String activitéPrincipal) {
		this.activitéPrincipal = activitéPrincipal;
	}

	public String getActivitéSecondaire() {
		return activitéSecondaire;
	}

	public void setActivitéSecondaire(String activitéSecondaire) {
		this.activitéSecondaire = activitéSecondaire;
	}

	public String getNomCommercial() {
		return nomCommercial;
	}

	public void setNomCommercial(String nomCommercial) {
		this.nomCommercial = nomCommercial;
	}

	public String getTypeActivité() {
		return typeActivité;
	}

	public void setTypeActivité(String typeActivité) {
		this.typeActivité = typeActivité;
	}

	public boolean isMarquePublicitaire() {
		return marquePublicitaire;
	}

	public void setMarquePublicitaire(boolean marquePublicitaire) {
		this.marquePublicitaire = marquePublicitaire;
	}

	public boolean isTravauxPublics() {
		return travauxPublics;
	}

	public void setTravauxPublics(boolean travauxPublics) {
		this.travauxPublics = travauxPublics;
	}

}