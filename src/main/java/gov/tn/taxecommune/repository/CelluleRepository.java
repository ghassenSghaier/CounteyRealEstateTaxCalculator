package gov.tn.taxecommune.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gov.tn.taxecommune.entity.Cellule;

@Repository
public interface CelluleRepository extends JpaRepository<Cellule, Long> {

	Optional<Cellule>  findByCode(String celluleName);
	
	@Query("SELECT c FROM Cellule c inner join c.celluleRues cr inner join cr.rue r where r.codeRue=(:codeRue) and cr.encoursRue = true")
	Optional<Cellule> findByCodeRue(String codeRue);
}
