//package gov.tn.taxeCommune.entity;
//
//import java.time.ZonedDateTime;
//import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotBlank;
//
//@Entity
//public class Location {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//
//	@ManyToOne
//	Article article;
//
//	@ManyToOne
//	@JoinColumn(name = "contribuable_id")
//	Contribuable contribuable;
//
//	@NotBlank(message = "register time is mandatory")
//	@Column(name = "register_at")
//	ZonedDateTime registeredAt;
//
//	@NotBlank(message = "date Location is mandatory")
//	@Column(name = "dat_location")
//	private Date dateLocation;
//
//	public Location(Article article, Contribuable contribuable, Date dateLocation) {
//		super();
//		this.article = article;
//		this.contribuable = contribuable;
//		this.registeredAt = ZonedDateTime.now();
//		this.dateLocation = dateLocation;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public Article getArticle() {
//		return article;
//	}
//
//	public void setArticle(Article article) {
//		this.article = article;
//	}
//	
//	public Contribuable getContribuable() {
//		return contribuable;
//	}
//
//	public void setContribuable(Contribuable contribuable) {
//		this.contribuable = contribuable;
//	}
//
//	public ZonedDateTime getRegisteredAt() {
//		return registeredAt;
//	}
//
//	public void setRegisteredAt(ZonedDateTime registeredAt) {
//		this.registeredAt = registeredAt;
//	}
//
//	public Date getDateLocation() {
//		return dateLocation;
//	}
//
//	public void setDateLocation(Date dateLocation) {
//		this.dateLocation = dateLocation;
//	}
//
//}
