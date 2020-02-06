package gov.tn.taxecommune.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name="type_activite")
public class TypeArticle implements Serializable {

    private static final long serialVersionUID = 1L;   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false)
	private Long id;

	@Column(name = "code_type")
	private String codeType;

	@Column(name = "nom_type", unique = true)
	private String nomType;

	@OneToMany
	private List<Article> articles;

	public TypeArticle(String codeType, String nomType) {
		super();
		this.codeType = codeType;
		this.nomType = nomType;
	}

	public TypeArticle() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getNomType() {
		return nomType;
	}

	public void setNomType(String nomType) {
		this.nomType = nomType;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
