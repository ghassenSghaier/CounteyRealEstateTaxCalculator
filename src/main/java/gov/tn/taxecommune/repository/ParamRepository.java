package gov.tn.taxecommune.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.tn.taxecommune.entity.Parametre;

@Repository
public interface ParamRepository extends JpaRepository<Parametre, Long> {

	Optional<Parametre>  findByCodeParam(String CodeCoef);
}
