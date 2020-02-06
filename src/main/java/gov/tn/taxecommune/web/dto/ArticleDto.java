package gov.tn.taxecommune.web.dto;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import gov.tn.taxecommune.customAnnotations.ValidCodePostal;
import gov.tn.taxecommune.customAnnotations.ValidCodeRue;
import gov.tn.taxecommune.customAnnotations.ValidNumeroMunicipal;
import gov.tn.taxecommune.entity.ArticlePrestation;
import gov.tn.taxecommune.entity.CategorieArticle;

/**
 * Created by Keno&Kemo on 22.10.2017..
 */
public class ArticleDto {

	private Long id;

//	@ValidTripleNumericField
	@ValidNumeroMunicipal
	@NotNull(message = "numéro municipal is required")
	private String numeroMunicipal;

	@NotNull(message = "numero article dans le rue  is required")
	private int numeroArticle;

	@NotNull(message = "rue is required")
	@ValidCodeRue
	private String codeRue;

	@ValidCodePostal
	@NotBlank(message = "code postal is required")
	private String codePostal;

	@NotNull(message = "surface total is required")
	private double surfaceTotal;

	@NotNull(message = "prestation is required")
	private List<String> pres = new ArrayList<>();

//	@NotBlank(message = "type occupant is required")
//	private String choixCont;

	@NotBlank(message = "nom Propriétaire is required")
	private String nomProp;

	@NotBlank(message = "prenom Propriétaire is required")
	private String prenomProp;

	@NotBlank(message = "nom Entreprise Propriétaire is required")
	private String nomEntre;

	@NotNull(message = "Identifiant  is required")
	private String propIdentifiant;

	@NotNull(message = "type propriétaire  is required")
	private String typeProp;

	@NotNull(message = "numero de telephone  is required")
	private String numTel;

	@Email
	@NotNull(message = "email  is required")
	private String email;

	@NotNull(message = "adresse  is required")
	private String adresse;

	@NotNull(message = "codePostal  is required")
	private String codePostalProp;

	@NotNull(message = "ville  is required")
	private String ville;

	@NotBlank(message = "nom non Propriétaire is required")
	private String nonPropNom;

	@NotBlank(message = "prenom non Propriétaire is required")
	private String nonPropPrenom;

	@NotBlank(message = "nom Entreprise non Propriétaire is required")
	private String nonPropEntre;

	@NotNull(message = "Identifiant non propriétaire  is required")
	private String nonPropIds;

	private List<ArticlePrestation> prestations;

	private List<CategorieArticle> cvs;

	@NotNull(message = "numero rep article dans le rue  is required")
	private boolean numRep;

	public ArticleDto(Long id, @NotNull(message = "numéro municipal is required") String numeroMunicipal,
			@NotBlank(message = "code postal is required") String codePostal,
			@NotNull(message = "surface total is required") double surfaceTotal,
			@NotNull(message = "prestation is required") List<String> pres,
//			@NotBlank(message = "type occupant is required") String occupantArticle,
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
			String carteSejour) {
		super();
		this.id = id;
		this.numeroMunicipal = numeroMunicipal;
		this.codePostal = codePostal;
		this.surfaceTotal = surfaceTotal;
		this.pres = pres;
//		this.choixCont = occupantArticle;
		this.nomProp = nomProp;
		this.prenomProp = prenomProp;
		this.nomEntre = nomEntre;
		this.propIdentifiant = propIdentifiant;
		this.typeProp = typeProp;
		this.numTel = numTel;
		this.email = email;
		this.adresse = adresse;
		this.codePostalProp = codePostalProp;
		this.ville = ville;
		this.nonPropNom = nonPropNom;
		this.nonPropPrenom = nonPropPrenom;
		this.nonPropEntre = nonPropEntre;
		this.nonPropIds = nonPropIds;
		this.prestations = prestations;
		this.cvs = cvs;
		this.codeRue = codeRue;
	}

	public ArticleDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroMunicipal() {
		return numeroMunicipal;
	}

	public void setNumeroMunicipal(String numeroMunicipal) {
		this.numeroMunicipal = numeroMunicipal;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public double getSurfaceTotal() {
		return surfaceTotal;
	}

	public void setSurfaceTotal(double surfaceTotal) {
		this.surfaceTotal = surfaceTotal;
	}

	public List<String> getPres() {
		return pres;
	}

	public void setPres(List<String> pres) {
		this.pres = pres;
	}

	public List<ArticlePrestation> getPrestations() {
		return prestations;
	}

	public void setPrestations(List<ArticlePrestation> prestations) {
		this.prestations = prestations;
	}

	public String getCodeRue() {
		return codeRue;
	}

	public void setCodeRue(String codeRue) {
		this.codeRue = codeRue;
	}

	public List<CategorieArticle> getCvs() {
		return cvs;
	}

	public void setCvs(List<CategorieArticle> cvs) {
		this.cvs = cvs;
	}

//	public String getChoixCont() {
//		return choixCont;
//	}
//
//	public void setChoixCont(String choixCont) {
//		this.choixCont = choixCont;
//	}

	public String getNomProp() {
		return nomProp;
	}

	public void setNomProp(String nomProp) {
		this.nomProp = nomProp;
	}

	public String getPrenomProp() {
		return prenomProp;
	}

	public void setPrenomProp(String prenomProp) {
		this.prenomProp = prenomProp;
	}

	public String getNomEntre() {
		return nomEntre;
	}

	public void setNomEntre(String nomEntre) {
		this.nomEntre = nomEntre;
	}

	public String getPropIdentifiant() {
		return propIdentifiant;
	}

	public void setpropIdentifiant(String ids) {
		this.propIdentifiant = ids;
	}

	public String getTypeProp() {
		return typeProp;
	}

	public void setTypeProp(String typeProp) {
		this.typeProp = typeProp;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodePostalProp() {
		return codePostalProp;
	}

	public void setCodePostalProp(String codePostalProp) {
		this.codePostalProp = codePostalProp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getNonPropNom() {
		return nonPropNom;
	}

	public void setNonPropNom(String nonPropNom) {
		this.nonPropNom = nonPropNom;
	}

	public String getNonPropPrenom() {
		return nonPropPrenom;
	}

	public void setNonPropPrenom(String nonPropPrenom) {
		this.nonPropPrenom = nonPropPrenom;
	}

	public String getNonPropEntre() {
		return nonPropEntre;
	}

	public void setNonPropEntre(String nonPropEntre) {
		this.nonPropEntre = nonPropEntre;
	}

	public String getNonPropIds() {
		return nonPropIds;
	}

	public void setNonPropIds(String nonPropIds) {
		this.nonPropIds = nonPropIds;
	}

	public boolean getNumRep() {
		return numRep;
	}

	public void setNumRep(boolean numRep) {
		this.numRep = numRep;
	}

	public int getNumeroArticle() {
		return numeroArticle;
	}

	public void setNumeroArticle(int numeroArticle) {
		this.numeroArticle = numeroArticle;
	}

	public void setPropIdentifiant(String propIdentifiant) {
		this.propIdentifiant = propIdentifiant;
	}

	@Override
	public String toString() {
		return "ArticleDto [id=" + id + ", numeroMunicipal=" + numeroMunicipal + ", codePostal=" + codePostal
				+ ", surfaceTotal=" + surfaceTotal + ", pres=" + pres + ", nomProp=" + nomProp + ", prenomProp="
				+ prenomProp + ", nomEntre=" + nomEntre + ", propIdentifiant=" + propIdentifiant + ", typeProp="
				+ typeProp + ", numTel=" + numTel + ", email=" + email + ", adresse=" + adresse + ", codePostalProp="
				+ codePostalProp + ", ville=" + ville + ", nonPropNom=" + nonPropNom + ", nonPropPrenom="
				+ nonPropPrenom + ", nonPropEntre=" + nonPropEntre + ", nonPropIds=" + nonPropIds + ", prestations="
				+ prestations + ", cvs=" + cvs + ", codeRue=" + codeRue + ", numeroArticle=" + numeroArticle
				+ ", numRep=" + numRep + "]";
	}

}
