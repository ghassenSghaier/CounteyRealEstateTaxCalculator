package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.tn.taxecommune.entity.Cellule;
import gov.tn.taxecommune.entity.Secteur;
import gov.tn.taxecommune.entity.SecteurCellule;

@Repository
public interface SecteurCelluleRepository extends JpaRepository<SecteurCellule, Long> {

	
	Optional<List<SecteurCellule>> findBySecteurAndCellule(Secteur secteur,Cellule cellule);
	@Query("SELECT sc FROM SecteurCellule sc JOIN sc.secteur as secteur where secteur.code = (:codeSecteur)")
	List<SecteurCellule> findByCodeSecteur(String codeSecteur);

	@Query("SELECT  sc FROM SecteurCellule sc JOIN sc.cellule as Cellule WHERE Cellule.code = (:codeCellule)")
	List<SecteurCellule> findByCodeCellule(String codeCellule);

//	@Query("SELECT  sc FROM SecteurCellule sc left outer JOIN  sc.secteur as secteur WHERE secteur.code = (:codeArticle) AND sc.id=(:id)")
//	List<SecteurCellule> findByCodeSecteurAndIdNot(String codeArticle, Long id);
//
//	@Query("SELECT sc FROM SecteurCellule sc  right outer JOIN  sc.cellule as Cellule WHERE Cellule.code = (:codeCellule)")
//	List<SecteurCellule> findByCodeCelluleAndIdNot(String codeCellule);
//
//	@Query("SELECT sc FROM SecteurCellule sc JOIN sc.secteur as secteur WHERE secteur.code LIKE '%(:codeArticle)%' ORDER BY sc.id")
//	Page<SecteurCellule> findByCodeSecteurContainingOrderByIdAsc(String codeArticle, Pageable pageable);
//
//	@Query("SELECT  sc FROM SecteurCellule sc JOIN sc.cellule as Cellule  WHERE Cellule.code LIKE '%(:codeCellule)%' ORDER BY sc.id")
//	Page<SecteurCellule> findByCodeCelluleContainingOrderByIdAsc(String codeCellule, Pageable pageable);

//	Page<ArticlePrestation> findByTauxPrestationContainingOrderByIdAsc(double tauxPrestation, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT  sc FROM SecteurCellule sc JOIN sc.secteur")
	List<SecteurCellule> findAllEagerly();

//	@Query("SELECT ap FROM ArticlePrestation ap JOIN FETCH ap.Prestation WHERE ap.tauxPrestation = (:tauxPrestation)")
//	User findByTauxPrestationEagerly(@Param("tauxPrestation") double tauxPrestation);

	@Query("SELECT  sc FROM SecteurCellule sc JOIN sc.secteur WHERE sc.id = (:id)")
	SecteurCellule findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	@Query("SELECT  sc FROM SecteurCellule sc JOIN sc.secteur as secteur where secteur.code = (:codeArticle)")
	Boolean existsByCodeSecteur(String codeArticle);

}
