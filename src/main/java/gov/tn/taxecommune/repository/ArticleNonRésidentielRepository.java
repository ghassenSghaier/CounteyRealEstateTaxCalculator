//package gov.tn.taxeCommune.repository;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.stereotype.Repository;
//import gov.tn.taxeCommune.entity.ArticleNonRésidentiel;
//
//@Repository
//public interface ArticleNonRésidentielRepository extends ArticleRepository { 
////	@Query("SELECT r FROM Article a,Rue r where a.id=r.id and a.identifiant = ?1")
//	Optional<ArticleNonRésidentiel> findById(long id);
//    List<ArticleNonRésidentiel> findByActPrincipal(String act_principal);
//	List<ArticleNonRésidentiel> findByActSecondaire(String act_secondaire);
//	List<ArticleNonRésidentiel> findByMarqPublicitaire(boolean marq);
//	List<ArticleNonRésidentiel> findByTravauxPublics(boolean tb);
//	List<ArticleNonRésidentiel> findBytypActivité(String acnr);   
//}