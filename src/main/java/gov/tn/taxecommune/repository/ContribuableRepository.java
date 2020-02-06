package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Contribuable;

@Repository
public interface ContribuableRepository extends UserRepository {
	List<Contribuable> findByNom(String nom);
	Optional<Contribuable> findById(long id);
}