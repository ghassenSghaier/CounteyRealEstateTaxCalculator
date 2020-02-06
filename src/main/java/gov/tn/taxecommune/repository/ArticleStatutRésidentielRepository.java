package gov.tn.taxecommune.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.ArticleStatutRésidentiel;

@Repository
public interface ArticleStatutRésidentielRepository extends JpaRepository<ArticleStatutRésidentiel, Long> {

	@Query("SELECT  ass FROM ArticleStatutRésidentiel ass JOIN FETCH ass.article as articles where articles.numeroMunicipal = (:NumeroMunicipal)")
	List<ArticleStatutRésidentiel> findByCodeArticle(String NumeroMunicipal);

	@Query("SELECT  ass FROM ArticleStatutRésidentiel ass JOIN FETCH ass.statutResidentiel as statut where statut.codeStatut = (:codeStatut)")
	List<ArticleStatutRésidentiel> findByCodeStatut(String codeStatut);

//	@Query("SELECT  ass FROM ArticleStatutRésidentiel ass RIGHT OUTER JOIN FETCH ass.article as articles where articles.numeroMunicipal = (:numeroMunicipal) AND articles.id = (:id)")
//	List<ArticleStatutRésidentiel> findByCodeArticleAndIdNot(String numeroMunicipal, Long id);

//	@Query("SELECT  ass FROM ArticleStatutRésidentiel ass LEFT OUTER JOIN FETCH ass.statutRésidentiel as statut where statut.codeStatut = (:codeStatut)")
//	List<ArticleStatutRésidentiel> findByCodeStatutAndIdNot(String codeStatut);

//	@Query("SELECT  ass FROM ArticleStatutRésidentiel ass JOIN ass.article as articles where articles.numeroMunicipal LIKE '%(:numeroMunicipal)%' ORDER BY id")
//	Page<ArticleStatutRésidentiel> findByCodeArticleContainingOrderByIdAsc(String numeroMunicipal, Pageable pageable);
//
//	@Query("SELECT  ass FROM ArticleStatutRésidentiel ass JOIN ass.statutRésidentiel as statut where statut.codeStatut LIKE '%(:codeStatut)%' ORDER BY id")
//	Page<ArticleStatutRésidentiel> findByCodeStatutContainingOrderByIdAsc(String codeStatut, Pageable pageable);
//
//	Page<ArticleStatutRésidentiel> findByEncoursArticleStatutRésidentielContainingOrderByIdAsc(
//			boolean encoursArticleStatutRésidentiel, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT  ass FROM ArticleStatutRésidentiel ass JOIN FETCH ass.article")
	List<ArticleStatutRésidentiel> findAllEagerly();

	@Query("SELECT  ass FROM ArticleStatutRésidentiel ass JOIN FETCH ass.article WHERE ass.encoursArticleStatutRésidentiel = (:encoursArticleStatutRésidentiel)")
	ArticleStatutRésidentiel findByEncoursArticleStatutRésidentielEagerly(
			@Param("encoursArticleStatutRésidentiel") boolean encoursArticleStatutRésidentiel);

//	@Query("SELECT  ass FROM ArticleStatutRésidentiel ass JOIN FETCH ass.article WHERE ass.id = (:id)")
//	ArticleStatutRésidentiel findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

//    @Query("SELECT  as FROM ArticleStatutRésidentiel,Article a JOIN FETCH as.article where a.numeroMunicipal = (:numeroMunicipal)")
//    Boolean existsByCodeArticle(String codeArticle);

}
