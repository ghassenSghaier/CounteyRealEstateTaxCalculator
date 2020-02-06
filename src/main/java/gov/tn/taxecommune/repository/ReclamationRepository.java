package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Reclamation;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {

	Reclamation findByCodeReclamation(String codeReclamation);

	Optional<List<Reclamation>> findByMotifReclamation(String codeMotif);

	//Reclamation findByCodeReclamationAndIdNot(String codeReclamation, Long id);

//    Reclamation findByReclamationnameAndIdNot(String Reclamationname, Long id);

	//Page<Reclamation> findByCodeReclamationContainingOrderByIdAsc(String codeReclamation, Pageable pageable);

	//Page<Reclamation> findByMotifReclamationContainingOrderByIdAsc(String motifReclamation, Pageable pageable);

//	@Query("SELECT r FROM Reclamation r inner JOIN r.articleTaxation ar inner join ar.taxation t  inner join t.article where a like (:codeArticle) order by r.id asc")
//	Page<Reclamation> findByCodeArticleContainingOrderByIdAsc(String codeArticle, Pageable pageable);
//
//	@Query("SELECT r FROM Reclamation r inner JOIN r.articleTaxation ar inner join ar.taxation t where t like (:codeTaxation) order by r.id asc")
//	Page<Reclamation> findByCodeTaxationContainingOrderByIdAsc(String codeTaxation, Pageable pageable);

	@Query("SELECT r FROM Reclamation r inner JOIN r.articleTaxation at inner join at.taxation t inner join t.article a inner join a.contribuableArticle ca inner join ca.contribuable c where ca.enCours = true and c.username = (:username)")
	Page<Reclamation> findAllForUser(String username, Pageable pageable);

	@Query("SELECT att FROM ArticleTaxation att inner join  att.taxation t inner join t.article a inner join a.contribuableArticle ca inner join ca.contribuable c where ca.enCours = true and c.username = (:username)")
	List<Reclamation> findAllAtForUser(String username);

//    Page<Reclamation> findByNomContainingOrderByIdAsc(String name, Pageable pageable);
//
//    Page<Reclamation> findByPrenomContainingOrderByIdAsc(String surname, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT r FROM Reclamation r inner JOIN r.articleTaxation ar inner join ar.taxation")
	List<Reclamation> findAllEagerly();

	@Query("SELECT r FROM Reclamation r inner JOIN r.articleTaxation ar inner join ar.taxation WHERE r.codeReclamation = (:codeReclamation)")
	Reclamation findByCodeReclamationEagerly(@Param("codeReclamation") String codeReclamation);

	@Query("SELECT r FROM Reclamation r inner JOIN r.articleTaxation ar inner join ar.taxation WHERE r.id = (:id)")
	Reclamation findByIdEagerly(@Param("id") Long id);

	@Query("SELECT r FROM Reclamation r inner JOIN r.articleTaxation ar inner join ar.taxation t inner join  t.article a left join a.contribuableArticle cas inner join cas.contribuable c WHERE c.username = (:username) and cas.enCours = true")
	Optional<List<Reclamation>> findByCASEagerly(@Param("username") String username);

	@Query("SELECT r FROM Reclamation r inner JOIN r.articleTaxation ar inner join ar.taxation t inner join t.article a  left join a.contribuableArticle cas inner join cas.article a WHERE a.numeroMunicipal = (:numeroMunicipal) and cas.enCours = true")
	Optional<List<Reclamation>> findByCAS1Eagerly(@Param("numeroMunicipal") String numeroMunicipal);

	@Query("SELECT r FROM Reclamation r inner JOIN r.articleTaxation ar inner join ar.taxation t inner join t.article a  left join a.contribuableArticle cas inner join cas.contribuable c inner join c.contribuableArticles ca inner join ca.article a WHERE c.username = (:username) and cas.enCours = true and a.numeroMunicipal = (:numeroMunicipal) and r.dateReclamation = year(sysdate())")
	Optional<Reclamation> findByUserAndArticle(@Param("username") String username,
			@Param("numeroMunicipal") long numeroMunicipal);

	@Query("SELECT r FROM Reclamation r inner JOIN r.articleTaxation ar inner join ar.taxation t inner JOIN t.article a left join a.contribuableArticle cas WHERE cas.enCours = true and t.code = (:codeTaxation)")
	Optional<List<Reclamation>> findByTaxationEagerly(@Param("codeTaxation") String codeReclamation);

	// ==========================================================================
	// endregion

	Boolean existsByCodeReclamation(String codeReclamation);

}
