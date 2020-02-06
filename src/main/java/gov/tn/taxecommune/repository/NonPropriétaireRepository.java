//package gov.tn.taxeCommune.repository;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.stereotype.Repository;
//import gov.tn.taxeCommune.entity.Contribuable;
//import gov.tn.taxeCommune.entity.Propriétaire;
//import gov.tn.taxeCommune.repository.ContribuableRepository;
//
//@Repository
//public interface NonPropriétaireRepository extends ContribuableRepository {
//	Optional<Contribuable> findById(long id);
//    List<Propriétaire> findByEmail(String email);
//    List<Propriétaire> findByVille(String ville);
//    List<Propriétaire> findByTelNo(long telno);            
//}