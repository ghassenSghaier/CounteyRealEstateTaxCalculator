package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	Article findByNumeroMunicipalNot(Long id);

	Optional<Article> findByNumeroMunicipal(String numeroMunicipal);
	
	//Article findByIdNot(Long id);
	
	Article findByNumeroMunicipalNot(String numeroMunicipal);

	Page<Article> findByCodePostalContainingOrderByNumeroMunicipalAsc(String codePostal, Pageable pageable);

	Page<Article> findByRepArticleContainingOrderByNumeroMunicipalAsc(int repArticle, Pageable pageable);

	Page<Article> findBySurfaceTotalContainingOrderByNumeroMunicipalAsc(double SurfaceTotal, Pageable pageable);

	Page<Article> findBySurfaceCouvertContainingOrderByNumeroMunicipalAsc(double SurfaceCouvert, Pageable pageable);

//	@Query("SELECT a FROM Article a,Prestation p JOIN FETCH a.prestations WHERE p.nomPrestation = (:prestation)")
//	Page<Article> findByPrestationContainingOrderByNumeroMunicipalAsc(String prestation, Pageable pageable);
	// region Find eagerly

	// ==========================================================================
	@Query("SELECT a FROM Article a JOIN FETCH a.articlerues")
	List<Article> findAllEagerly();
	
	@Query("SELECT a FROM Article a  WHERE a.numeroMunicipal like SUBSTRING((:numeroMunicipal),0,10) order By a.numeroMunicipal desc")
	List<Article> findByNumRep(String numeroMunicipal);

	@Query("SELECT a FROM Article a JOIN FETCH a.articlerues WHERE a.numeroMunicipal = (:numeroMunicipal)")
	Article findByNumeroMunicipalEagerly(@Param("numeroMunicipal") String numeroMunicipal);

	// ==========================================================================
	// endregion

	Boolean existsByNumeroMunicipal(String numeroMunicipal);

	void deleteByNumeroMunicipal(String numeroMunicipal);	
}
