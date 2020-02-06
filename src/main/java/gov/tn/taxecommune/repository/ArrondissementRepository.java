package gov.tn.taxecommune.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Arrondissement;

@Repository
public interface ArrondissementRepository extends JpaRepository<Arrondissement, Long> {
	Arrondissement findByNom(String name);
	Optional<Arrondissement> findByCode(String code);
}
