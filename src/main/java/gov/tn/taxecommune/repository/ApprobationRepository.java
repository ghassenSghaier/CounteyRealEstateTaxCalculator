//package gov.tn.taxeCommune.repository;
//
//import java.util.Date;
//import java.util.List;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//import gov.tn.taxeCommune.entity.Approbation;
//import gov.tn.taxeCommune.entity.Article;
//import gov.tn.taxeCommune.entity.CaractèrePropriété;
//
//@Repository
//public interface ApprobationRepository extends CrudRepository<Approbation, Long> {
////	@Query("SELECT a FROM Approbation a,Propriétaire p where a.id=p.id and p.identifiant = ?1")
////    List<Approbation> findByPIdentifiant(long identifiant);
//	@Query("SELECT a FROM Approbation ap,Article a where ap.id=a.id and a.identifiant = ?1")
//	List<Article> findByArIdentifiant(long identfiant);
//
////    List<Approbation> findByVille(String ville);
////    List<Approbation> findByTel_no(long telno);
//	List<Approbation> findByRegisteredAt(Date RegisteredAt);
//
//	List<Approbation> findByCaracProp(CaractèrePropriété cp);
//
//	List<Approbation> findByDateAcquisition(Date dateAcquisition);
//}