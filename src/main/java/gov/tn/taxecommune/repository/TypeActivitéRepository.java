package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.TypeActivité;
import gov.tn.taxecommune.entity.User;

@Repository
public interface TypeActivitéRepository extends JpaRepository<TypeActivité, Long> {

	Optional<TypeActivité> findByCodeType(String codeType);

	Optional<TypeActivité> findByNomType(String codeType);

//	TypeActivité findByNomTypeAndIdNot(String codeType, Long id);
//
//	TypeActivité findByCodeTypeAndIdNot(String codeType, Long id);
//
//	Page<TypeActivité> findByCodeTypeContainingOrderByIdAsc(String codeType, Pageable pageable);
//
//	Page<TypeActivité> findByNomTypeContainingOrderByIdAsc(String nomType, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT t FROM TypeActivité t JOIN FETCH t.articleActivite")
	List<User> findAllEagerly();

	@Query("SELECT t FROM TypeActivité t JOIN FETCH t.articleActivite WHERE t.nomType = (:nomType)")
	User findByNomTypeEagerly(@Param("nomType") String nomType);

//	@Query("SELECT t FROM TypeActivité t JOIN FETCH t.articleActivite WHERE t.id = (:id)")
//	User findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	Boolean existsByCodeType(String codeType);

//    @Query("SELECT c FROM User c where c.username = ?1 AND c.password = ?2")    
//    User findByLoginAndPassword(String username, String password);
}
