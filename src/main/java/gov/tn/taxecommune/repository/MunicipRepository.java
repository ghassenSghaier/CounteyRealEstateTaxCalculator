package gov.tn.taxecommune.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Municipalité;

@Repository
public interface MunicipRepository extends JpaRepository<Municipalité, Long> {

	Municipalité findByNom(String nom);
	Optional<Municipalité> findByCodeMunicip(String codeMunicip);

}
