package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.tn.taxecommune.entity.Categorie;

@Repository
public interface CategorieVocationRepository extends JpaRepository<Categorie, Long> {

	Optional<Categorie> findByCodeCategorie(String codeCategorie);

	Categorie findByNomCategorie(String nomCategorie);

	Categorie findByMaxCouvert(double maxCouvert);

	Categorie findByMinCouvert(double maxCouvert);

	@Query("SELECT c FROM Categorie c WHERE (:size) BETWEEN c.maxCouvert and c.minCouvert")
	List<Categorie> findByIntervalCouvert(double size);
	
	@Query("SELECT c FROM Categorie c join c.vocation v WHERE (:size) BETWEEN c.maxCouvert and c.minCouvert  and v.nomVocation=(:nomVocation)")
	Categorie findByNomVocationAndIntervalCouvert(double size,String nomVocation);

	//Categorie findByNomCategorieAndIdNot(String nomCategorie, Long id);

	//Categorie findByCodeCategorieAndIdNot(String codeCategorie, Long id);

	//Page<Categorie> findByNomCategorieContainingOrderByIdAsc(String nomCategorie, Pageable pageable);

	//Page<Categorie> findByCodeCategorieContainingOrderByIdAsc(String codeCategorie, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
//	@Query("SELECT c FROM Categorie c JOIN FETCH c.vocation")
//	List<Categorie> findAllEagerly();
//
//	@Query("SELECT c FROM Categorie c JOIN FETCH c.vocation WHERE c.nomCategorie = (:nomCategorie)")
//	Categorie findByNomCategorieEagerly(@Param("nomCategorie") String nomCategorie);
//
//	@Query("SELECT c FROM Categorie c JOIN FETCH c.vocation WHERE c.id = (:id)")
//	Categorie findByIdEagerly(@Param("id") Long id);
	
	@Query("SELECT c FROM Categorie c JOIN FETCH c.vocation v WHERE v.nomVocation = (:nomVocation)")
	List<Categorie> findByNomVocationEagerly(@Param("nomVocation") String nomVocation);
	
//	@Query("SELECT c FROM Categorie c JOIN FETCH c.categorieTarificationprestations c1 WHERE t.nomVocation = (:nomVocation)")
//	Categorie findByCategorieAndPrestation(@Param("nomVocation") String nomVocation);

	// ==========================================================================
	// endregion

	Boolean existsByNomCategorie(String nomCategorie);
}
