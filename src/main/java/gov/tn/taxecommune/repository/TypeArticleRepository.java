package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.TypeArticle;

@Repository
public interface TypeArticleRepository extends JpaRepository<TypeArticle, Long> {

	Optional<TypeArticle> findByCodeType(String codeType);

	Optional<TypeArticle> findByNomType(String codeType);

//	TypeArticle findByNomTypeAndIdNot(String codeType, Long id);
//
//	TypeArticle findByCodeTypeAndIdNot(String codeType, Long id);

//	Page<TypeArticle> findByCodeTypeContainingOrderByIdAsc(String codeType, Pageable pageable);
//
//	Page<TypeArticle> findByNomTypeContainingOrderByIdAsc(String nomType, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT t FROM TypeArticle t JOIN FETCH t.articles")
	List<TypeArticle> findAllEagerly();

	@Query("SELECT t FROM TypeArticle t JOIN FETCH t.articles WHERE t.nomType = (:nomType)")
	TypeArticle findByNomTypeEagerly(@Param("nomType") String nomType);

//	@Query("SELECT t FROM TypeArticle t JOIN FETCH t.articles WHERE t.id = (:id)")
//	TypeArticle findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	Boolean existsByCodeType(String codeType);

//    @Query("SELECT c FROM User c where c.username = ?1 AND c.password = ?2")    
//    User findByLoginAndPassword(String username, String password);
}
