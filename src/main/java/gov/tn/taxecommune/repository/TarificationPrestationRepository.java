package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Tarificationprestation;

@Repository
public interface TarificationPrestationRepository extends JpaRepository<Tarificationprestation, Long> {

	Optional<Tarificationprestation> findByCodeTarification(String codeTarification);

	Tarificationprestation findBynbMinPrestation(int nbPrestations);

	Tarificationprestation findBynbMaxPrestation(int nbPrestations);

	//Tarificationprestation findByTauxPrestationAndIdNot(double tauxPrestation, Long id);

//	Page<Tarificationprestation> findByCodeTarificationContainingOrderByIdAsc(String codeTarification,
//			Pageable pageable);
//
//	Page<Tarificationprestation> findByTauxPrestationContainingOrderByIdAsc(double tauxPrestation, Pageable pageable);
//
//	Page<Tarificationprestation> findByNbMinPrestationContainingOrderByIdAsc(int nbPrestation, Pageable pageable);
//
//	Page<Tarificationprestation> findByNbMaxPrestationContainingOrderByIdAsc(int nbPrestation, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
//    @Query("SELECT t FROM TarificationPrestation t JOIN FETCH u.roles")
//    List<TarificationPrestation> findAllEagerly();
//
//    @Query("SELECT u FROM TarificationPrestation u JOIN FETCH u.roles WHERE u.email = (:email)")
//    TarificationPrestation findByEmailEagerly(@Param("email") String email);
//
	@Query("SELECT t FROM Tarificationprestation t where  (:nbPrestation) between  t.nbMinPrestation  and  t.nbMaxPrestation")
	Tarificationprestation findByNbPrestations(@Param("nbPrestation") int nbPrestation);

	// ==========================================================================
	// endregion

	Boolean existsByCodeTarification(String codeTarification);

//    @Query("SELECT c FROM TarificationPrestation c where c.TarificationPrestationname = ?1 AND c.password = ?2")    
//    TarificationPrestation findByLoginAndPassword(String TarificationPrestationname, String password);
}
