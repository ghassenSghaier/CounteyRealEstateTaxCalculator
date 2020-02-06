package gov.tn.taxecommune.message.request;

import java.util.Set;

import javax.validation.constraints.NotBlank;

public class AddContribuableForm{

		
	@NotBlank(message="could not be blank")
	private String identifiant;

	private String nom;
	
	private String prenom;
	
	private String username;
	
	private String password;

	private String statut;
	
	private String email;

	private String adresse;

	private String ville;

	private String codePostal;

	private long numeroTel;
	
	private String numeroCin;

	private String codeFiscal;

	private String numPasseport;

	private String carteSejour;
	
	private Set<String> userRoles;	
								
	public AddContribuableForm(String username,String password,String identifiant, String nom, String prenom, String statut, String email,
			String adresse, String ville, String codePostal, long numeroTel, String numeroCin, String codeFiscal,
			String numPasseport, String carteSejour, Set<String> userRoles) {
		super();
		this.username=username;
		this.password=password;
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.statut = statut;
		this.email = email;
		this.adresse = adresse;
		this.ville = ville;
		this.codePostal = codePostal;
		this.numeroTel = numeroTel;
		this.numeroCin = numeroCin;
		this.codeFiscal = codeFiscal;
		this.numPasseport = numPasseport;
		this.carteSejour = carteSejour;
		this.userRoles = userRoles;
	}

	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getStatut() {
		return statut;
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

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public long getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(long numeroTel) {
		this.numeroTel = numeroTel;
	}

	public String getNumeroCin() {
		return numeroCin;
	}

	public void setNumeroCin(String numero_cin) {
		this.numeroCin = numero_cin;
	}

	public String getCodeFiscal() {
		return codeFiscal;
	}

	public void setCodeFiscal(String codeFiscal) {
		this.codeFiscal = codeFiscal;
	}

	public String getNumPasseport() {
		return numPasseport;
	}

	public void setNumPasseport(String num_passeport) {
		this.numPasseport = num_passeport;
	}

	public String getCarteSejour() {
		return carteSejour;
	}

	public void setCarteSejour(String carteSejour) {
		this.carteSejour = carteSejour;
	}

	public Set<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<String> userRoles) {
		this.userRoles = userRoles;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	

//	@OneToMany(mappedBy = "contribuable", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<Approbation> approbations;
//
//	@OneToMany(mappedBy = "contribuable", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<Location> locations;

//	@OneToOne
//	protected Identifiant identifiant;
//
//	@Null
//	@Column(name = "estAdmin")
//	private boolean estAdmin;
	
	

	}
