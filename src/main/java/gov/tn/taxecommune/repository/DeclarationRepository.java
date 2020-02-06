package gov.tn.taxecommune.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Declaration;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long> {

	Optional<Declaration> findByAdresseAndNumDeclare(String adresse, int num);

	Optional<List<Declaration>> findByAdresse(String adresse);

	Page<Declaration> findByStatutDeclarantContainingOrderByCodeDeclarationAsc(String StatutDeclaration, Pageable pageable);

	Page<Declaration> findByEtatDeclareContainingOrderByCodeDeclarationAsc(String etatDeclaration, Pageable pageable);

	Page<Declaration> findBySurfaceTotalContainingOrderByCodeDeclarationAsc(double surfaceTotal, Pageable pageable);

	Page<Declaration> findBySurfaceNBContainingOrderByCodeDeclarationAsc(double surfaceNB, Pageable pageable);

	Page<Declaration> findByValVenaleContainingOrderByCodeDeclarationAsc(double valVenale, Pageable pageable);

	Page<Declaration> findByCheminContainingOrderByCodeDeclarationAsc(String chemin, Pageable pageable);

	Page<Declaration> findByNumDeclareContainingOrderByCodeDeclarationAsc(int numDeclare, Pageable pageable);

	Page<Declaration> findByActCommercialContainingOrderByCodeDeclarationAsc(String actCommercial, Pageable pageable);

	Page<Declaration> findBySignatureContainingOrderByCodeDeclarationAsc(String signature, Pageable pageable);

	Page<Declaration> findByDateDeclarationContainingOrderByCodeDeclarationAsc(Date Decaration, Pageable pageable);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT d FROM Declaration d JOIN FETCH d.controles")
	List<Declaration> findAllEagerly();

	@Query("SELECT d FROM Declaration d JOIN FETCH d.controles inner join d.user user WHERE user.email = (:email)")
	Optional<List<Declaration>> findByEmailEagerly(@Param("email") String email);

	@Query("SELECT d FROM Declaration d JOIN FETCH d.controles  WHERE d.codeDeclaration = (:id)")
	Declaration findByCodeDeclarationEagerly(@Param("id") String id);

	// ==========================================================================
	// endregion

	// Boolean existsByDeclarationname(String Declarationname);
	// @Query("SELECT c FROM Declaration c where c.Declarationname = ?1 AND
	// c.password = ?2")
	// Declaration findByLoginAndPassword(String Declarationname, String password);
}
