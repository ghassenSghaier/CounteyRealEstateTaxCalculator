package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Article;
import gov.tn.taxecommune.entity.Categorie;
import gov.tn.taxecommune.entity.CategorieArticle;

@Repository
public interface CategorieArticleRepository extends JpaRepository<CategorieArticle, Long> {
	Optional<List<CategorieArticle>> findByCategorieAndArticle(Categorie categorie, Article article);

	@Query("SELECT ca FROM CategorieArticle ca JOIN ca.categorie as categorie where categorie.codeCategorie = (:codeCategorie)")
	List<CategorieArticle> findByCodeCategorie(String codeCategorie);

	@Query("SELECT ca FROM CategorieArticle ca JOIN ca.article as article WHERE article.numeroMunicipal = (:numeroMunicipal)")
	List<CategorieArticle> findByCodeArticle(String numeroMunicipal);

//	@Query("SELECT ca FROM CategorieArticle ca left outer JOIN  ca.categorie as categorie WHERE categorie.codeCategorie = (:codeCategorie) AND ca.id=(:id)")
//	List<CategorieArticle> findByCodeCategorieAndIdNot(String codeCategorie, Long id);
//
//	@Query("SELECT ca FROM CategorieArticle ca  right outer JOIN  ca.article as article WHERE article.numeroMunicipal = (:numeroMunicipal) AND ca.id=(:id)")
//	List<CategorieArticle> findByCodeArticleAndIdNot(String numeroMunicipal, Long id);

//	@Query("SELECT ca FROM CategorieArticle ca JOIN ca.article as article WHERE article.code LIKE '%(:codeArticle)%' ORDER BY ass.id")
//	Page<MunicipAron> findByCodeMunicipContainingOrderByIdAsc(String codeArticle, Pageable pageable);
//
//	@Query("SELECT  ass FROM AronSecteur ass JOIN ass.aron as aron  WHERE aron.code LIKE '%(:codeAron)%' ORDER BY ass.id")
//	Page<MunicipAron> findByCodeAronContainingOrderByIdAsc(String codeAron, Pageable pageable);

//	Page<ArticlePrestation> findByTauxPrestationContainingOrderByIdAsc(double tauxPrestation, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT ca FROM CategorieArticle ca JOIN ca.categorie")
	List<CategorieArticle> findAllEagerly();

//	@Query("SELECT ap FROM ArticlePrestation ap JOIN FETCH ap.Prestation WHERE ap.tauxPrestation = (:tauxPrestation)")
//	User findByTauxPrestationEagerly(@Param("tauxPrestation") double tauxPrestation);

//	@Query("SELECT ca FROM CategorieArticle ca JOIN ca.article WHERE ca.id = (:id)")
//	CategorieArticle findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	@Query("SELECT ca FROM CategorieArticle ca JOIN ca.article as article where article.numeroMunicipal = (:numeroMunicipal)")
	Boolean existsByCodeArticle(String numeroMunicipal);

}
