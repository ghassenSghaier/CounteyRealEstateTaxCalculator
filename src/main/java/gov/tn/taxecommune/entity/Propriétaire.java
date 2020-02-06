//package gov.tn.taxeCommune.entity;
//
//import java.util.EnumSet;
//import java.util.Set;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.NaturalIdCache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//
//@Entity
//@Table(name = "propriétaire")
//@NaturalIdCache
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//public class Propriétaire extends Contribuable {
//
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue
//	private Long id;
//
//	@Email
//	@Column(name = "email", unique = true)
//	private String email;
//
//	@NotBlank(message = "Adresse is mandatory")
//	@Column(name = "adresse", length = 12)
//	private String adresse;
//
//	@NotBlank(message = "ville is mandatory")
//	@Column(name = "ville", length = 12)
//	private String ville;
//
//	@NotBlank(message = "code postal is mandatory")
//	@Column(name = "cod_pos", length = 4)
//	private String codPos;
//
//	@Column(name = "tel_no", length = 8)
//	private long telNo;
//
//	@OneToMany(mappedBy = "propriétaire", cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<Approbation> approbations;
//
//	public Propriétaire(@NotBlank(message = "username is mandatory") String username,
//			@NotBlank(message = "password is mandatory") String password,
//			@NotBlank(message = "nom is mandatory") String nom,
//			@NotBlank(message = "prenom is mandatory") String prenom, @NotNull Statut statut,
//			EnumSet<TypIdentifiant> typsIndetifiants, Identifiant identifiant,
//			@NotBlank(message = "Email is mandatory") String email,
//			@NotBlank(message = "Adresse is mandatory") String adresse,
//			@NotBlank(message = "ville is mandatory") String ville,
//			@NotBlank(message = "code postal is mandatory") String cod_pos, long tel_no) {
//		super(username, password, nom, prenom, statut, typsIndetifiants, identifiant);
//		// TODO Auto-generated constructor stub
//		this.email = email;
//		this.adresse = adresse;
//		this.ville = ville;
//		this.codPos = cod_pos;
//		this.telNo = tel_no;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public String getAdresse() {
//		return adresse;
//	}
//
//	public void setAdresse(String adresse) {
//		this.adresse = adresse;
//	}
//
//	public String getVille() {
//		return ville;
//	}
//
//	public void setVille(String ville) {
//		this.ville = ville;
//	}
//
//	public String getCod_pos() {
//		return codPos;
//	}
//
//	public void setCodPos(String codPos) {
//		this.codPos = codPos;
//	}
//
//	public long getTelNoo() {
//		return telNo;
//	}
//
//	public void setTelNo(long telNo) {
//		this.telNo = telNo;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
////	public List<ArticlePropriétaire> getArticles() {
////		return articles;
////	}
////
////	public void setArticles(List<ArticlePropriétaire> articles) {
////		this.articles = articles;
////	}
//
//}
