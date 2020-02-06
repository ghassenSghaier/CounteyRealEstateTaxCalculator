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
import gov.tn.taxecommune.entity.CelluleRue;
import gov.tn.taxecommune.entity.Rue;

@Repository
public interface CelluleRueRepository extends JpaRepository<CelluleRue, Long> {

	Optional<List<CelluleRue>> findByCelluleAndRue(Cellule cellule,Rue rue);
	
	@Query("SELECT sc FROM CelluleRue sc JOIN sc.rue as rue where rue.codeRue = (:coderue)")
	List<CelluleRue> findByCodeRue(String coderue);

	@Query("SELECT  sc FROM CelluleRue sc JOIN sc.cellule as Cellule WHERE Cellule.code = (:codeCellule)")
	List<CelluleRue> findByCodeCellule(String codeCellule);

//	@Query("SELECT  sc FROM CelluleRue sc left outer JOIN  sc.rue as rue WHERE rue.codeRue = (:codeArticle) AND sc.id=(:id)")
//	List<CelluleRue> findByCoderueAndIdNot(String codeArticle, Long id);
//
//	@Query("SELECT sc FROM CelluleRue sc  right outer JOIN  sc.cellule as Cellule WHERE Cellule.code = (:codeCellule)")
//	List<CelluleRue> findByCodeCelluleAndIdNot(String codeCellule);
//
//	@Query("SELECT sc FROM CelluleRue sc JOIN sc.rue as rue WHERE rue.codeRue LIKE '%(:codeArticle)%' ORDER BY sc.id")
//	Page<CelluleRue> findByCoderueContainingOrderByIdAsc(String codeArticle, Pageable pageable);
//
//	@Query("SELECT  sc FROM CelluleRue sc JOIN sc.cellule as Cellule  WHERE Cellule.code LIKE '%(:codeCellule)%' ORDER BY sc.id")
//	Page<CelluleRue> findByCodeCelluleContainingOrderByIdAsc(String codeCellule, Pageable pageable);

//	Page<ArticlePrestation> findByTauxPrestationContainingOrderByIdAsc(double tauxPrestation, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT  sc FROM CelluleRue sc JOIN sc.rue")
	List<CelluleRue> findAllEagerly();

//	@Query("SELECT ap FROM ArticlePrestation ap JOIN FETCH ap.Prestation WHERE ap.tauxPrestation = (:tauxPrestation)")
//	User findByTauxPrestationEagerly(@Param("tauxPrestation") double tauxPrestation);

	@Query("SELECT  sc FROM CelluleRue sc JOIN sc.rue WHERE sc.id = (:id)")
	CelluleRue findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	@Query("SELECT  sc FROM CelluleRue sc JOIN sc.rue as rue where rue.codeRue = (:codeArticle)")
	Boolean existsByCoderue(String codeArticle);

}
