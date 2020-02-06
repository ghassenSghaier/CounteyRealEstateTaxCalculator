package gov.tn.taxecommune.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Created by Keno&Kemo on 22.10.2017..
 */
@Entity
public class Declaration implements Serializable {

    private static final long serialVersionUID = 1L;    

	@Id
	@NotNull
        @Column(name="code_declaration")	
	private String codeDeclaration;
                
        @Column(name="statut_declarant")
	private String statutDeclarant;

        @Column(name="adresse")
	private String adresse;

        @Column(name="etat_declare")
	private String etatDeclare;

        @Column(name="surface_total")
	private double surfaceTotal;

        @Column(name="surface_non_bati")
	private double surfaceNB;

        @Column(name="surface_bati")
	private double surfaceB;

        @Column(name="valeur_venale")
	private double valVenale;

        @Column(name="chemin")
	private String chemin;

        @Column(name="numero_declare")
	private int numDeclare;

        @Column(name="statut_expl_declare")
	private String statutExplDeclare;

        @Column(name="activite_commercial")
	private String actCommercial;

	private ArrayList<?> prestations;

        @Column(name="signature")
	private String signature;

        @Column(name="date_declaration")
	private Date dateDeclaration;

	@OneToMany(mappedBy = "declaration")
	private List<Controle> controles;

	@ManyToOne
	private User user;

	public Declaration() {
		super();
		this.prestations = new ArrayList<String>();
	}	

	public String getCodeDeclaration() {
		return codeDeclaration;
	}

	public void setCodeDeclaration(String codeDeclaration) {
		this.codeDeclaration = codeDeclaration;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	public List<?> getPrestations() {
		return prestations;
	}

	public void setPrestations(ArrayList<?> prestations) {
		this.prestations = prestations;
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

	public List<Controle> getControles() {
		return controles;
	}

	public void setControles(List<Controle> controles) {
		this.controles = controles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
