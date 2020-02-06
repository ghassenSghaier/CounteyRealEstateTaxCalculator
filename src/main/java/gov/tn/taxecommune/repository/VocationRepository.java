package gov.tn.taxecommune.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.tn.taxecommune.entity.Vocation;

@Repository
public interface VocationRepository extends JpaRepository<Vocation, Long> {

	Optional<Vocation> findByCodeVocation(String codeVocation);
	Vocation findByNomVocation(String nomVocation);

	//Vocation findByNomVocationAndIdNot(String nomVocation, Long id);

	//Vocation findByCodeVocationAndIdNot(String nomVocation, Long id);

	//Page<Vocation> findByNomVocationContainingOrderByIdAsc(String nomVocation, Pageable pageable);

	//Page<Vocation> findByCodeVocationContainingOrderByIdAsc(String codeVocation, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
//    @Query("SELECT v FROM Vocation v JOIN FETCH v.categorie")
//    List<Vocation> findAllEagerly();
//
//    @Query("SELECT v FROM Vocation v JOIN FETCH v.categorie WHERE v.nomVocation = (:nomVocation)")
//    Vocation findByNomVocationEagerly(@Param("nomVocation") String nomVOcation);
//
//    @Query("SELECT v FROM Vocation v JOIN FETCH v.categorie WHERE v.id = (:id)")
//    Vocation findByIdEagerly(@Param("id") Long id);

	// ==========================================================================
	// endregion

	Boolean existsByNomVocation(String Vocationname);

}
