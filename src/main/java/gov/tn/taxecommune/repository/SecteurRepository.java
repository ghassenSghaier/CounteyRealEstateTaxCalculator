package gov.tn.taxecommune.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Secteur;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long> {

	Optional<Secteur> findByCode(String celluleName);
}
