//package gov.tn.taxeCommune.entity;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//
//@Embeddable
//public class ArticlePropriétaireId implements Serializable {
//    
//	private static final long serialVersionUID = 1L;
//
//	@Column(name = "article_id")
//    private Long articleId;
// 
//    @Column(name = "propriétaire_id")
//    private Long propriétaireId;
// 
//    ArticlePropriétaireId() {}
// 
//    ArticlePropriétaireId(Long articleId,Long propriétaireId) {
//        this.articleId = articleId;
//        this.propriétaireId = propriétaireId;
//    }
// 
//    //Getters omitted for brevity
// 
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
// 
//        if (o == null || getClass() != o.getClass())
//            return false;
// 
//        ArticlePropriétaireId that = (ArticlePropriétaireId) o;
//        return Objects.equals(articleId, that.articleId) &&
//               Objects.equals(propriétaireId, that.propriétaireId);
//    }
// 
//    @Override
//    public int hashCode() {
//        return Objects.hash(articleId, propriétaireId);
//    }
//}