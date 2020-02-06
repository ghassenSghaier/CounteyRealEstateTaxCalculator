package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.CategorieTarificationprestation;
import gov.tn.taxecommune.entity.Categorie;
import gov.tn.taxecommune.entity.Tarificationprestation;

@Repository
public interface CategorieTarificationPrestationRepository
		extends JpaRepository<CategorieTarificationprestation, Long> {

	Optional<CategorieTarificationprestation> findByCodeCTPrestation(String codeCategorie);

	CategorieTarificationprestation findByNomCTPrestation(String nomCategorie);

//	CategorieTarificationprestation findByNomCTPrestationAndIdNot(String nomCategorie, Long id);
//
//	CategorieTarificationprestation findByCodeCTPrestationAndIdNot(String codePrestation, Long id);
//
//	Page<CategorieTarificationprestation> findByCodeCTPrestationContainingOrderByIdAsc(String codePrestation,
//			Pageable pageable);
//
//	Page<CategorieTarificationprestation> findByNomCTPrestationContainingOrderByIdAsc(String email, Pageable pageable);

	Optional<List<CategorieTarificationprestation>> findByACategorie(Categorie cv);

	Optional<List<CategorieTarificationprestation>> findByTPrestation(Tarificationprestation tp);

	// region Find eagerly
	// ==========================================================================
//    @Query("SELECT c FROM CategorieTarificationPrestation c JOIN FETCH u.roles")
//    List<User> findAllEagerly();

//    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = (:email)")
//    User findByEmailEagerly(@Param("email") String email);

//    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = (:id)")
//    User findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	Boolean existsByCodeCTPrestation(String username);

//	@Query("SELECT c FROM CategorieTarificationPrestation c JOIN c.tPrestation as tPrestations where tPrestations.codeTarification = (:codeTarification)")
//	@Query(value = "select c from CategorieTarificationPrestation c join TarificationPrestation t   on t.id = c.tPrestation_id where t.codeTarification = :codeTarification", nativeQuery = true)
	@Query(value = "select ctp from CategorieTarificationprestation ctp join ctp.aCategorie c join ctp.tPrestation tp   where tp.codeTarification = (:codeTarification) and c.codeCategorie = (:codeCategorie) and ctp.encoursctPrestation = true")
	CategorieTarificationprestation findByCodeTarificationAndCodeCategorie(String codeTarification,String codeCategorie);

//	@Query("SELECT c FROM CategorieTarificationPrestation c JOIN c.cVocation as cvocation where cvocation.codeCategorie = (:codeCategorie)")
//	@Query(value = "select c from CategorieTarificationprestation c join c.aCategorie as ca where ca.codeCategorie = (:codeCategorie)")
//	Optional<List<CategorieTarificationprestation>> findByCodeCategorie(String codeCategorie);

//    @Query("SELECT c FROM User c where c.username = ?1 AND c.password = ?2")    
//    User findByLoginAndPassword(String username, String password);
	
//	@Query(value = "select c from CategorieTarificationprestation c inner join fetch c.aCategorie as ca inner join fetch c.tPrestation as tp  where ca.codeCategorie = ( :codeCategorie) and   tp.codeTarification = ( :codeTarification)")
//	CategorieTarificationprestation findByCategorieTarificationPrestation(String  codeCategorie, String codeTarification);

}
