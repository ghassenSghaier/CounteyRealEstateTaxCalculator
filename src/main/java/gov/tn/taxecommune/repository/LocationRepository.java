//package gov.tn.taxeCommune.repository;
//import java.util.Date;
//import java.util.List;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//import gov.tn.taxeCommune.entity.Location;
//
//@Repository
//public interface LocationRepository extends CrudRepository <Location, Long> { 
////	@Query("SELECT l FROM Location l,NonPropriétaire n where l.id=n.id and n.identifiant = ?1")
////    List<Location> findByNpIdentifiant(long identfiant);
////	@Query("SELECT a FROM Location l,article a where l.id=a.id and a.identifiant = ?1")
////    List<Article> findByArIdentifiant(long identfiant);		
//    List<Location> findByDateLocation(Date date);
//    List<Location> findByRegisteredAt(Date register);
//           
//}