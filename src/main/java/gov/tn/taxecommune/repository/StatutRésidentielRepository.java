package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.StatutRésidentiel;

@Repository
public interface StatutRésidentielRepository extends JpaRepository<StatutRésidentiel, Long> {

	Optional<StatutRésidentiel> findByCodeStatut(String codeType);

	Optional<StatutRésidentiel> findByNomStatut(String nomStatut);

//	StatutRésidentiel findByNomStatutAndIdNot(String nomStatut, Long id);
//
//	StatutRésidentiel findByCodeStatutAndIdNot(String codeStatut, Long id);

//	Page<StatutRésidentiel> findByCodeStatutContainingOrderByIdAsc(String codeStatut, Pageable pageable);
//
//	Page<StatutRésidentiel> findByNomStatutContainingOrderByIdAsc(String nomStatut, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT s FROM StatutRésidentiel s JOIN FETCH s.articlestatutRésidence")
	List<StatutRésidentiel> findAllEagerly();

	@Query("SELECT s FROM StatutRésidentiel s JOIN FETCH s.articlestatutRésidence WHERE s.nomStatut = (:nomStatut)")
	StatutRésidentiel findByNomStatutEagerly(@Param("nomStatut") String nomStatut);

//	@Query("SELECT s FROM StatutRésidentiel s JOIN FETCH s.articlestatutRésidence WHERE s.id = (:id)")
//	StatutRésidentiel findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	Boolean existsByCodeStatut(String codeStatut);

//    @Query("SELECT c FROM User c where c.username = ?1 AND c.password = ?2")    
//    User findByLoginAndPassword(String username, String password);
}
