//package gov.tn.taxeCommune.repository;
//
//import java.util.List;
//import java.util.Optional;
//import org.springframework.stereotype.Repository;
//import gov.tn.taxeCommune.entity.ArticleRésidentiel;
//import gov.tn.taxeCommune.entity.StatutRésidentiel;
//import gov.tn.taxeCommune.entity.TypRésidentiel;
//
//
//@Repository
//public interface ArticleRésidentielRepository extends ArticleRepository { 
////	@Query("SELECT r FROM Article a,Rue r where a.id=r.id and a.identifiant = ?1")
//	Optional<ArticleRésidentiel> findById(long id);
//    List<ArticleRésidentiel> findByStatutRsdl(StatutRésidentiel statut);
//	List<ArticleRésidentiel> findByTypeRsdl(TypRésidentiel typR);       
//}