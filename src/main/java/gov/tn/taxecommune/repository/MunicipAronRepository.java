package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.tn.taxecommune.entity.Arrondissement;
import gov.tn.taxecommune.entity.MunicipAron;
import gov.tn.taxecommune.entity.Municipalité;

@Repository
public interface MunicipAronRepository extends JpaRepository<MunicipAron, Long> {

	@Query("SELECT  ma FROM MunicipAron ma JOIN ma.municip as municip where municip.codeMunicip = (:codeMunicip)")
	List<MunicipAron> findByCodeMunicip(String codeMunicip);

	@Query("SELECT  ma FROM MunicipAron ma JOIN ma.aron as aron WHERE aron.code = (:codeAron)")
	List<MunicipAron> findByCodeAron(String codeAron);

//	@Query("SELECT  ma FROM MunicipAron ma left outer JOIN  ma.municip as municip WHERE municip.codeMunicip = (:codeArticle) AND ma.id=(:id)")
//	List<MunicipAron> findByCodeArticleAndIdNot(String codeArticle, Long id);
//
//	@Query("SELECT  ma FROM MunicipAron ma right outer JOIN  ma.aron as aron WHERE aron.code = (:codeAron)")
//	List<MunicipAron> findByCodeAronAndIdNot(String codeAron);
//
//	@Query("SELECT  ma FROM MunicipAron ma JOIN ma.municip as municip WHERE municip.codeMunicip LIKE '%(:codeArticle)%' ORDER BY ma.id")
//	Page<MunicipAron> findByCodeMunicipContainingOrderByIdAsc(String codeArticle, Pageable pageable);
//
//	@Query("SELECT  ma FROM MunicipAron ma JOIN ma.aron as aron  WHERE aron.code LIKE '%(:codeAron)%' ORDER BY ma.id")
//	Page<MunicipAron> findByCodeAronContainingOrderByIdAsc(String codeAron, Pageable pageable);

//	Page<ArticlePrestation> findByTauxPrestationContainingOrderByIdAsc(double tauxPrestation, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT  ma FROM MunicipAron ma JOIN  ma.municip")
	List<MunicipAron> findAllEagerly();

//	@Query("SELECT ap FROM ArticlePrestation ap JOIN FETCH ap.Prestation WHERE ap.tauxPrestation = (:tauxPrestation)")
//	User findByTauxPrestationEagerly(@Param("tauxPrestation") double tauxPrestation);

//	@Query("SELECT  ma FROM MunicipAron ma JOIN  ma.municip WHERE ma.id = (:id)")
//	MunicipAron findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	@Query("SELECT  ma FROM MunicipAron ma JOIN ma.municip as municip where municip.codeMunicip = (:codeArticle)")
	Boolean existsByCodeMunicip(String codeArticle);

	Optional<List<MunicipAron>> findByMunicipAndAron(Municipalité municip, Arrondissement aron);

}
