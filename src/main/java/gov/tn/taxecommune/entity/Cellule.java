package gov.tn.taxecommune.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Null;

@Entity
public class Cellule implements Serializable {

    private static final long serialVersionUID = 1L;    

	@Id
	@NotNull
	@Column(name = "code_cellule", length = 6, unique = true)
	private String code;

	@JsonManagedReference
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "cellule",fetch = FetchType.LAZY, orphanRemoval = true)
	private List<CelluleRue> celluleRues;

        @JsonManagedReference
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "cellule",fetch = FetchType.LAZY, orphanRemoval = true)
	private List<SecteurCellule> secteurCellules;

	public Cellule(String code, List<CelluleRue> celluleRues, List<SecteurCellule> secteurCellules) {
		super();
		this.code = code;
		this.celluleRues = celluleRues;
		this.secteurCellules = secteurCellules;
	}

	public Cellule(String code, List<CelluleRue> celluleRues) {
		super();
		this.code = code;
		this.celluleRues = celluleRues;
	}

	public Cellule(String code) {
		super();
		this.code = code;
	}

	public Cellule() {
		super();
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<CelluleRue> getCelluleRues() {
		return celluleRues;
	}

	public void setCelluleRues(List<CelluleRue> celluleRues) {
		this.celluleRues = celluleRues;
	}

	public List<SecteurCellule> getSecteurCellules() {
		return secteurCellules;
	}

	public void setSecteurCellules(List<SecteurCellule> secteurCellules) {
		this.secteurCellules = secteurCellules;
	}

}
