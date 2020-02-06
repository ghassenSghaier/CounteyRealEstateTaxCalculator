//package gov.tn.taxeCommune.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Repository;
//
//import gov.tn.taxeCommune.entity.ArticleTerreNue;
//
//@Repository
//public interface ArticleTerreNueRepository extends ArticleRepository { 
////	@Query("SELECT r FROM Article a,Rue r where a.id=r.id and a.identifiant = ?1")
//	List<ArticleTerreNue> findByTaxeM2D(double taxeM2D);
//	List<ArticleTerreNue> findByValVenale(double taxeM2D);
//	Optional<ArticleTerreNue> findById(long id);
////	List<ArticleNonRésidentiel> findByActSecondaire(String act_secondaire);
////	List<ArticleNonRésidentiel> findByMarq_publicitaire(boolean marq);
////	List<ArticleNonRésidentiel> findByTravaux_publics(boolean tb);
////	List<ArticleNonRésidentiel> findByTypActivité(ActivitéNonRésidentiel acnr);   
//	
//}