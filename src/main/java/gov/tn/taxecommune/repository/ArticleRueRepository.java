package gov.tn.taxecommune.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.tn.taxecommune.entity.ArticleRue;

@Repository
public interface ArticleRueRepository extends JpaRepository<ArticleRue, Long> {

	@Query("SELECT  ar FROM ArticleRue ar JOIN ar.article as articles where articles.numeroMunicipal = (:codeArticle)")
	List<ArticleRue> findByCodeArticle(String codeArticle);

	@Query("SELECT  ar FROM ArticleRue ar JOIN ar.rue as rue WHERE rue.codeRue = (:codeRue)")
	List<ArticleRue> findByCodeRue(String codeRue);

	//@Query("SELECT  ar FROM ArticleRue ar left outer JOIN  ar.article as articles WHERE articles.numeroMunicipal = (:codeArticle) AND ar.id=(:id)")
	//List<ArticleRue> findByCodeArticleAndIdNot(String codeArticle, Long id);

	//@Query("SELECT  ar FROM ArticleRue ar right outer JOIN  ar.rue as rues WHERE rues.codeRue = (:codeRue)")
	//List<ArticleRue> findByCodeRueAndIdNot(String codeRue);

	//@Query("SELECT  ar FROM ArticleRue ar JOIN ar.article as articles WHERE articles.numeroMunicipal LIKE '%(:codeArticle)%' ORDER BY ar.id")
	//Page<ArticleRue> findByCodeArticleContainingOrderByIdAsc(String codeArticle, Pageable pageable);

	//@Query("SELECT  ar FROM ArticleRue ar JOIN ar.rue as rue  WHERE rue.codeRue LIKE '%(:codeRue)%' ORDER BY ar.id")
	//Page<ArticleRue> findByCodePrestationContainingOrderByIdAsc(String codePrestation, Pageable pageable);

//	Page<ArticlePrestation> findByTauxPrestationContainingOrderByIdAsc(double tauxPrestation, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT  ar FROM ArticleRue ar JOIN ar.article")
	List<ArticleRue> findAllEagerly();

//	@Query("SELECT ap FROM ArticlePrestation ap JOIN FETCH ap.Prestation WHERE ap.tauxPrestation = (:tauxPrestation)")
//	User findByTauxPrestationEagerly(@Param("tauxPrestation") double tauxPrestation);

	//@Query("SELECT  ar FROM ArticleRue ar JOIN ar.article WHERE ar.id = (:id)")
	//ArticleRue findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	@Query("SELECT  ar FROM ArticleRue ar JOIN ar.article as articles where articles.numeroMunicipal = (:codeArticle)")
	Boolean existsByCodeArticle(String codeArticle);

}
