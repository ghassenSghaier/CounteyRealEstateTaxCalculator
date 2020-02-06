package gov.tn.taxecommune.web.dto;

import javax.validation.constraints.NotBlank;

import gov.tn.taxecommune.customAnnotations.PasswordMatches;
import gov.tn.taxecommune.customAnnotations.ValidEmail;
import gov.tn.taxecommune.entity.Role;

import java.util.Set;

/**
 * Created by Keno&Kemo on 22.10.2017..
 */
@PasswordMatches
public class UserDto {

	private Long id;

	@NotBlank(message = "Nom is required")
	private String nom;

	@NotBlank(message = "Prenom is required")
	private String prenom;

	@NotBlank(message = "Username is required")
	private String username;

	@ValidEmail
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Password is required")
	private String password;

	@NotBlank(message = "Matching password is required")
	private String matchingPassword;

	private boolean enabled;

	public UserDto(Long id, String nom, String prenom, String username, String email, String password,
			String matchingPassword, Set<Role> roles, boolean enabled) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.email = email;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.enabled = enabled;
	}

	public UserDto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String name) {
		this.prenom = name;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String surname) {
		this.prenom = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
