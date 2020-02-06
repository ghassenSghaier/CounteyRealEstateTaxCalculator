package gov.tn.taxecommune.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Degrevement;
//import gov.tn.taxecommune.entity.Taxation;

@Repository
public interface DegrevementRepository extends JpaRepository<Degrevement, Long> {

	Degrevement findByCodeDegrevement(String codeDegrevement);

	List<Degrevement> findByTypeDegrevement(String typeDegrevement);

	List<Degrevement> findByStatutDegrevement(String statutDegrevement);

	List<Degrevement> findByDecisionDegrevement(String decisionDegrevement);

	List<Degrevement> findBymontantDegrevement(double montantDegrevement);

	Page<Degrevement> findByTypeDegrevementContainingOrderByCodeDegrevementAsc(String type, Pageable pageable);

	Page<Degrevement> findByStatutDegrevementContainingOrderByCodeDegrevementAsc(String statut, Pageable pageable);

	Page<Degrevement> findByDecisionDegrevementContainingOrderByCodeDegrevementAsc(String decision, Pageable pageable);

	Page<Degrevement> findByMontantDegrevementContainingOrderByCodeDegrevementAsc(double montant, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT d FROM Degrevement d inner join d.articleTaxation at join at.taxation t join t.article  a where a.numeroMunicipal = (:numeroMunicipal)")
	List<Degrevement> findAllEagerly();

//    @Query("SELECT u FROM Taxation u JOIN FETCH u.roles WHERE u.email = (:email)")
//    Taxation findByEmailEagerly(@Param("email") String email);

	@Query("SELECT d FROM Degrevement d inner join d.articleTaxation at join at.taxation t join t.article  a where d.codeDegrevement = (:codeDegrevement)")
	Degrevement findByCodeDegrevementEagerly(@Param("codeDegrevement") String codeDegrevement);

	// ==========================================================================
	// endregion

}
