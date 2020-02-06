package gov.tn.taxecommune.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Taxation;

@Repository
public interface TaxationRepository extends JpaRepository<Taxation, Long> {

	Optional<Taxation> findByCode(String codeTaxation);
	
	List<Taxation> findByMontantTCL(double montantTcl);

	List<Taxation> findByMontantTIB(double montantTib);

	List<Taxation> findByMontantFNAH(double montantFnah);

	List<Taxation> findByMontantTTNB(double montantTtnb);

//	Page<Taxation> findByMontantTCLContainingOrderByIdAsc(double tcl, Pageable pageable);
//
//	Page<Taxation> findByMontantTIBContainingOrderByIdAsc(double tib, Pageable pageable);
//
//	Page<Taxation> findByMontantFNAHContainingOrderByIdAsc(double fnah, Pageable pageable);
//	
//	Page<Taxation> findByAnneeTaxationContainingOrderByIdAsc(Date date, Pageable pageable);
	
//	@Query("SELECT t FROM Taxation t inner JOIN t.article a where a.numeroMunicipal = (:numeroMunicipal) order by t.id")
//	Page<Taxation> findByCodeArticleContainingOrderByIdAsc(String numeroMunicipal, Pageable pageable);
//
//	Page<Taxation> findByMontantTTNBContainingOrderByIdAsc(double ttnb, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT t FROM Taxation t JOIN FETCH t.article a where a.numeroMunicipal = (:numeroMunicipal)")
	List<Taxation> findAllEagerly();

//    @Query("SELECT u FROM Taxation u JOIN FETCH u.roles WHERE u.email = (:email)")
//    Taxation findByEmailEagerly(@Param("email") String email);

//	@Query("SELECT t FROM Taxation t  JOIN FETCH t.article WHERE t.id = (:id)")
//	Taxation findByIdEagerly(@Param("id") Long id);
	
	@Query("SELECT t FROM Taxation t INNER JOIN t.article a WHERE a.numeroMunicipal = (:numeroMunicipal)")
	List<Taxation> findByArticle(@Param("numeroMunicipal") String numeroMunicipal);

	// ==========================================================================
	// endregion

}
