package gov.tn.taxecommune.web.dto;

import gov.tn.taxecommune.customAnnotations.ValidRoleName;
//import lombok.AllArgsConstructor;
import lombok.Data;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;

/**
 * Created by Keno&Kemo on 08.12.2017..
 */
@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class RoleDto {
	Long id;
	@ValidRoleName
	String nom;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
