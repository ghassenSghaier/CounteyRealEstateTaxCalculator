package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.Prestation;

@Repository
public interface PrestationRepository extends JpaRepository<Prestation, Long> {

    Optional<Prestation> findByCodePrestation(String codePrestation);

    Optional<Prestation> findByNomPrestation(String nomPrestation);

//    Prestation findByNomPrestationAndIdNot(String nomPrestation, Long id);
//
//    Prestation findByCodePrestationAndIdNot(String codePrestation, Long id);
//
//    Page<Prestation> findByCodePrestationContainingOrderByIdAsc(String codePrestation, Pageable pageable);
//
//    Page<Prestation> findByNomPrestationContainingOrderByIdAsc(String nomPrestation, Pageable pageable);

    //region Find eagerly
    //==========================================================================
    @Query("SELECT p FROM Prestation p JOIN FETCH p.articlePrestation")
    List<Prestation> findAllEagerly();

    @Query("SELECT p FROM Prestation p JOIN FETCH p.articlePrestation WHERE p.nomPrestation = (:nomPrestation)")
    Prestation findByNomPrestationEagerly(@Param("nomPrestation") String nomPrestation);

//    @Query("SELECT p FROM Prestation p JOIN FETCH p.articlePrestation WHERE p.id = (:id)")
//    Prestation findByIdEagerly(@Param("id") Long id);

    //==========================================================================
    //endregion
    Boolean existsByNomPrestation(String nomPrestation);

//    @Query("SELECT c FROM User c where c.username = ?1 AND c.password = ?2")    
//    User findByLoginAndPassword(String username, String password);
}
