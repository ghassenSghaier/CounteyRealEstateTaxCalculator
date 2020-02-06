package gov.tn.taxecommune.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.tn.taxecommune.entity.Densite;

@Repository
public interface DensitéRepository extends JpaRepository<Densite, Long> {
	Optional<Densite> findByNomDensité(String nomDensité);
}
