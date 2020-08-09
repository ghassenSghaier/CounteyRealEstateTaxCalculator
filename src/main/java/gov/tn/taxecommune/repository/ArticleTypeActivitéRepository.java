package gov.tn.taxecommune.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.ArticleTypeActivité;

@Repository
public interface ArticleTypeActivitéRepository extends JpaRepository<ArticleTypeActivité, Long> {

	@Query("SELECT  at FROM ArticleTypeActivité at JOIN at.article as articles where articles.numeroMunicipal = (:codeArticle)")
	List<ArticleTypeActivité> findByCodeArticle(String codeArticle);

	@Query("SELECT  at FROM ArticleTypeActivité at JOIN at.typeActivite as types where types.codeType = (:codeType)")
	List<ArticleTypeActivité> findByCodeType(String codeType);

	//@Query("SELECT  at FROM ArticleTypeActivité at LEFT OUTER JOIN  at.article as articles where articles.numeroMunicipal = (:codeArticle) AND at.id=(:id)")
	//List<ArticleTypeActivité> findByCodeArticleAndIdNot(String codeArticle, Long id);

	//@Query("SELECT  at FROM ArticleTypeActivité at RIGHT OUTER JOIN at.typeActivite as types where types.codeType = (:codeType)")
	//List<ArticleTypeActivité> findByCodePrestationAndIdNot(String codeType);

	//@Query("SELECT  at FROM ArticleTypeActivité at LEFT JOIN  at.article as articles where articles.numeroMunicipal LIKE '%(:codeArticle)%' ORDER BY id")
	//Page<ArticleTypeActivité> findByCodeArticleContainingOrderByIdAsc(String codeArticle, Pageable pageable);

	//@Query("SELECT  at FROM ArticleTypeActivité at JOIN at.typeActivite as types where types.codeType LIKE '%(:codeType)%' ORDER BY id")
	//Page<ArticleTypeActivité> findByCodeTypeContainingOrderByIdAsc(String codeType, Pageable pageable);

	//Page<ArticleTypeActivité> findByOuvertureArticletypeactiviteContainingOrderByIdAsc(
	//		Date ouvertureArticletypeactivité, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT at FROM ArticleTypeActivité at JOIN at.article")
	List<ArticleTypeActivité> findAllEagerly();

	@Query("SELECT at FROM ArticleTypeActivité at JOIN at.article WHERE at.ouvertureArticletypeactivite = (:ouvertureArticletypeactivité)")
	ArticleTypeActivité findByOuvertureArticletypeactiviteEagerly(
			@Param("ouvertureArticletypeactivité") Date ouvertureArticletypeactivité);

	//@Query("SELECT at FROM ArticleTypeActivité at JOIN at.article WHERE at.id = (:id)")
	//ArticleTypeActivité findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	@Query("SELECT at FROM ArticleTypeActivité at JOIN at.article as articles where articles.numeroMunicipal = (:codeArticle)")
	Boolean existsByCodeArticle(String codeArticle);
}
