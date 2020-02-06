//package gov.tn.taxeCommune.entity;
//
//import java.time.LocalDateTime;
////import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Entity
//public class ArticleParamètre {
//
//	@Id
//	Long id;
//
//	@ManyToOne
//	@JoinColumn(name = "article_id")
//	private Article article;
//
//	@ManyToOne
//	@JoinColumn(name = "paramètre_id")
//	private Paramètre paramètre;
//
//	LocalDateTime registeredAt;
//
//	public Paramètre getParamètre() {
//		return paramètre;
//	}
//
//	public void setParamètre(Paramètre paramètre) {
//		this.paramètre = paramètre;
//	}
//
//	public LocalDateTime getRegisteredAt() {
//		return registeredAt;
//	}
//
//	public void setRegisteredAt(LocalDateTime registeredAt) {
//		this.registeredAt = registeredAt;
//	}
//
//}