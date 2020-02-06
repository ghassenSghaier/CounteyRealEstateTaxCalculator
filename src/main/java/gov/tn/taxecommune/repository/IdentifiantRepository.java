//package gov.tn.taxeCommune.repository;
//
//import java.util.List;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//import gov.tn.taxeCommune.entity.Identifiant;
//
//@Repository
//public interface IdentifiantRepository extends CrudRepository <Identifiant, Long> {     
//    @Query("SELECT i FROM Identifiant i,User u where i.id=u.id AND u.username= ?1")
//    List<Identifiant> findByIdentifiant(String username);            
//}