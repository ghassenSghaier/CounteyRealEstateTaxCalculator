package gov.tn.taxecommune.web.dto;

import javax.validation.constraints.NotBlank;

import gov.tn.taxecommune.customAnnotations.ValidEmail;
import gov.tn.taxecommune.entity.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keno&Kemo on 03.12.2017..
 */
public class UserUpdateDto {

    private Long id;

    @NotBlank (message = "Nom is required")
    private String nom;

    @NotBlank (message = "Prenom is required")
    private String prenom;

    @NotBlank (message = "Username is required")
    private String username;

    @ValidEmail
    @NotBlank (message = "Email is required")
    private String email;

    private List<Role> roles = new ArrayList<>();

    private boolean enabled;

    public UserUpdateDto(Long id, String name, String surname, String username, String email, boolean enabled) {
        this.id = id;
        this.nom = name;
        this.prenom = surname;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
    }

    public UserUpdateDto(Long id, String name, String surname, String username, String email, boolean enabled,
                         List<Role> roles) {
        this.id = id;
        this.nom = name;
        this.prenom = surname;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
    }

    public UserUpdateDto() {

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
        this.nom = name;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}