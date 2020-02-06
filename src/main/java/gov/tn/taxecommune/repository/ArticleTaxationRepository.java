package gov.tn.taxecommune.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.tn.taxecommune.entity.ArticleTaxation;

@Repository
public interface ArticleTaxationRepository extends JpaRepository<ArticleTaxation, Long> {

	List<ArticleTaxation> findByMontantPayment(double montantTcl);

	List<ArticleTaxation> findByMontantPaymentFNAH(double montantTib);

	//Page<ArticleTaxation> findByMontantPaymentContainingOrderByIdAsc(double tcl, Pageable pageable);

	//Page<ArticleTaxation> findByMontantPaymentFNAHContainingOrderByIdAsc(double tib, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT at FROM ArticleTaxation at inner join at.taxation t inner join t.article a where a.numeroMunicipal = (:numeroMunicipal)")
	List<ArticleTaxation> findAllEagerly();

//    @Query("SELECT u FROM Taxation u JOIN FETCH u.roles WHERE u.email = (:email)")
//    Taxation findByEmailEagerly(@Param("email") String email);

//	@Query("SELECT at FROM ArticleTaxation at inner join at.reclamation WHERE at.id = (:id)")
//	ArticleTaxation findByIdEagerly(@Param("id") Long id);

	@Query("SELECT att FROM ArticleTaxation att inner join att.taxation t inner join t.article a inner join a.contribuableArticle ca inner join ca.contribuable c where ca.enCours = true and c.username = (:username)")
	List<ArticleTaxation> findAllAtForUser(String username);

	@Query("SELECT  att FROM ArticleTaxation att inner join att.taxation t inner join t.article a inner join a.contribuableArticle ca inner join ca.contribuable c where ca.enCours = true and c.username = (:username) and a.numeroMunicipal =(:codeArticle) and AnnéeTaxation = year(sysdate())")
	ArticleTaxation findByArticleAndDate(String codeArticle, String username);

	// ==========================================================================
	// endregion

}
