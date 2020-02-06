package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Rue;

@Repository
public interface RueRepository extends JpaRepository<Rue, Long> {

	Optional<Rue> findByCodeRue(String codeRue);

	Page<Rue> findByClasseRueContainingOrderByCodeRueAsc(String classeRue, Pageable pageable);

	Page<Rue> findByNomRueContainingOrderByCodeRueAsc(String nomRue, Pageable pageable);

	Page<Rue> findByAutreClasseRueContainingOrderByCodeRueAsc(String autreClasse, Pageable pageable);
	// region Find eagerly

	// ==========================================================================
	@Query("SELECT r FROM Rue r JOIN FETCH r.celluleRues")
	List<Rue> findAllEagerly();

	@Query("SELECT r FROM Rue r JOIN FETCH r.celluleRues WHERE r.codeRue = (:codeRue)")
	Rue findByCodeRueEagerly(@Param("codeRue") String codeRue);

	// ==========================================================================
	// endregion

	Boolean existsByCodeRue(String codeRue);

	void deleteByCodeRue(String codeRue);
}
