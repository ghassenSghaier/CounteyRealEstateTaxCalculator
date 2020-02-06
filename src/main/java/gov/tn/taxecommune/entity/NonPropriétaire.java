//package gov.tn.taxeCommune.entity;
//
//import java.util.EnumSet;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//@Entity
//public class NonPropriétaire extends Contribuable {
//
//	@Id
//	@GeneratedValue
//	private Long id;
//
//	private static final long serialVersionUID = 1L;
//
//	public NonPropriétaire() {
//		super();
//	}
//
//	public NonPropriétaire(@NotBlank(message = "Username is mandatory") String username,
//			@NotBlank(message = "Password is mandatory") String password,
//			@NotBlank(message = "Prenom is mandatory") String prenom,
//			@NotBlank(message = "Nom is mandatory") String nom, @NotNull Statut statut,
//			EnumSet<TypIdentifiant> typsIndetifiants, Identifiant identifiant) {
//		super(username, password, nom, prenom, statut, typsIndetifiants, identifiant);
//	}
//
//}
