package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.tn.taxecommune.entity.Controle;

@Repository
public interface ControleRepository extends JpaRepository<Controle, Long> {

	@Query("SELECT c FROM Controle c inner JOIN c.declaration d where d.codeDeclaration = (:codeDeclaration)")
	Optional<List<Controle>> findByCodeDeclaration(String codeDeclaration);
	
//	@Query("SELECT c FROM Controle c inner JOIN c.declaration d where d.codeDeclaration = (:codeDeclaration) order by c.id asc")
//	Page<Controle> findByCodeDeclarationContainingOrderByIdAsc(String codeDeclaration, Pageable pageable);
//
//	@Query("SELECT c FROM Controle c inner JOIN c.declaration d inner join d.user u where u.username = (:username) order by c.id asc")
//	Page<Controle> findByUserContainingOrderByIdAsc(String username, Pageable pageable);

	@Query("SELECT c FROM Controle c inner JOIN c.declaration d inner join d.user u where u.username = (:username)")
	Optional<List<Controle>> findByUsername(String username);

	// region Find eagerly
	// ==========================================================================
	@Query("SELECT c FROM Controle c inner JOIN c.declaration")
	List<Controle> findAllEagerly();

	@Query("SELECT c FROM Controle c inner JOIN c.declaration d inner join d.user u WHERE u.email = (:email)")
	Controle findByEmailEagerly(@Param("email") String email);	

	// ==========================================================================
	// endregion

}
