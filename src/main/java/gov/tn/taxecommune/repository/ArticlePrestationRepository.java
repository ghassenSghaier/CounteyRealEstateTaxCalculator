package gov.tn.taxecommune.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.ArticlePrestation;

@Repository
public interface ArticlePrestationRepository extends JpaRepository<ArticlePrestation, Long> {

	@Query("SELECT  ap FROM ArticlePrestation ap JOIN ap.article as articles where articles.numeroMunicipal = (:codeArticle)")
	List<ArticlePrestation> findByCodeArticle(String codeArticle);

	@Query("SELECT  ap FROM ArticlePrestation ap JOIN ap.prestation as prestations WHERE prestations.codePrestation = (:codePrestation)")
	List<ArticlePrestation> findByCodePrestation(String codePrestation);

	//@Query("SELECT  ap FROM ArticlePrestation ap left outer JOIN  ap.article as articles WHERE articles.numeroMunicipal = (:codeArticle) AND ap.id=(:id)")
	//List<ArticlePrestation> findByCodeArticleAndIdNot(String codeArticle, Long id);

	//@Query("SELECT  ap FROM ArticlePrestation ap right outer JOIN  ap.prestation as prestations WHERE prestations.codePrestation = (:codePrestation)")
	//List<ArticlePrestation> findByCodePrestationAndIdNot(String codePrestation);

	//@Query("SELECT  ap FROM ArticlePrestation ap JOIN ap.article as articles WHERE articles.numeroMunicipal LIKE '%(:codeArticle)%' ORDER BY ap.id")
	//Page<ArticlePrestation> findByCodeArticleContainingOrderByIdAsc(String codeArticle, Pageable pageable);

	//@Query("SELECT  ap FROM ArticlePrestation ap JOIN ap.prestation as prestations  WHERE prestations.codePrestation LIKE '%(:codePrestation)%' ORDER BY ap.id")
	//Page<ArticlePrestation> findByCodePrestationContainingOrderByIdAsc(String codePrestation, Pageable pageable);

//	Page<ArticlePrestation> findByTauxPrestationContainingOrderByIdAsc(double tauxPrestation, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT ap FROM ArticlePrestation ap JOIN  ap.article")
	List<ArticlePrestation> findAllEagerly();

//	@Query("SELECT ap FROM ArticlePrestation ap JOIN FETCH ap.Prestation WHERE ap.tauxPrestation = (:tauxPrestation)")
//	User findByTauxPrestationEagerly(@Param("tauxPrestation") double tauxPrestation);

	//@Query("SELECT ap FROM ArticlePrestation ap JOIN  ap.article WHERE ap.id = (:id)")
	//ArticlePrestation findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	@Query("SELECT  ap FROM ArticlePrestation ap JOIN ap.article as articles where articles.numeroMunicipal = (:codeArticle)")
	Boolean existsByCodeArticle(String codeArticle);

}
