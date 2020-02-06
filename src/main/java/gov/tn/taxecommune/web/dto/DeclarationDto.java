package gov.tn.taxecommune.web.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

/**
 * Created by Keno&Kemo on 22.10.2017..
 */
public class DeclarationDto {

	private Long id;
	
	@NotBlank(message = "statut is required")
	private String statutDeclarant;

	@NotBlank(message = "etat déclaré is required")
	private String etatDeclare;

	private double surfaceTotal;

	private double surfaceNB;

	private double surfaceB;

	private double valVenale;

	private String chemin;

	private int numDeclare;

	private String statutExplDeclare;

	private String actCommercial;

	private List<String> services;

	private String signature;

	private Date dateDeclaration;
	
	private String adresse;

	public DeclarationDto(Long id, @NotBlank(message = "statut is required") String statutDeclarant,
			@NotBlank(message = "etat déclaré is required") String etatDeclare, double surfaceTotal, double surfaceNB,
			double surfaceB, double valVenale, String chemin, int numDeclare, String statutExplDeclare,
			String actCommercial) {
		super();
		this.id = id;
		this.statutDeclarant = statutDeclarant;
		this.etatDeclare = etatDeclare;
		this.surfaceTotal = surfaceTotal;
		this.surfaceNB = surfaceNB;
		this.surfaceB = surfaceB;
		this.valVenale = valVenale;
		this.chemin = chemin;
		this.numDeclare = numDeclare;
		this.statutExplDeclare = statutExplDeclare;
		this.actCommercial = actCommercial;
		services = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatutDeclarant() {
		return statutDeclarant;
	}

	public void setStatutDeclarant(String statutDeclarant) {
		this.statutDeclarant = statutDeclarant;
	}

	public String getEtatDeclare() {
		return etatDeclare;
	}

	public void setEtatDeclare(String etatDeclare) {
		this.etatDeclare = etatDeclare;
	}

	public double getSurfaceTotal() {
		return surfaceTotal;
	}

	public void setSurfaceTotal(double surfaceTotal) {
		this.surfaceTotal = surfaceTotal;
	}

	public double getSurfaceNB() {
		return surfaceNB;
	}

	public void setSurfaceNB(double surfaceNB) {
		this.surfaceNB = surfaceNB;
	}

	public double getSurfaceB() {
		return surfaceB;
	}

	public void setSurfaceB(double surfaceB) {
		this.surfaceB = surfaceB;
	}

	public double getValVenale() {
		return valVenale;
	}

	public void setValVenale(double valVenale) {
		this.valVenale = valVenale;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public int getNumDeclare() {
		return numDeclare;
	}

	public void setNumDeclare(int numDeclare) {
		this.numDeclare = numDeclare;
	}

	public String getStatutExplDeclare() {
		return statutExplDeclare;
	}

	public void setStatutExplDeclare(String statutExplDeclare) {
		this.statutExplDeclare = statutExplDeclare;
	}

	public String getActCommercial() {
		return actCommercial;
	}

	public void setActCommercial(String actCommercial) {
		this.actCommercial = actCommercial;
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Date getDateDeclaration() {
		return dateDeclaration;
	}

	public void setDateDeclaration(Date dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

}
