package gov.tn.taxecommune.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.AronSecteur;
import gov.tn.taxecommune.entity.Arrondissement;
import gov.tn.taxecommune.entity.MunicipAron;
import gov.tn.taxecommune.entity.Secteur;

@Repository
public interface AronSecteurRepository extends JpaRepository<AronSecteur, Long> {

    Optional<List<AronSecteur>> findByAronAndSecteur(Arrondissement aron, Secteur secteur);

    @Query("SELECT ass FROM AronSecteur ass JOIN ass.secteur as secteur where secteur.code = (:codeSecteur)")
    List<AronSecteur> findByCodeSecteur(String codeSecteur);

    @Query("SELECT  ass FROM AronSecteur ass JOIN ass.aron as aron WHERE aron.code = (:codeAron)")
    List<AronSecteur> findByCodeAron(String codeAron);

    //@Query("SELECT  ass FROM AronSecteur ass left outer JOIN  ass.secteur as secteur WHERE secteur.code = (:codeArticle) AND ass.id=(:id)")
    //List<AronSecteur> findByCodeArticleAndIdNot(String codeArticle, Long id);
    //@Query("SELECT ass FROM AronSecteur ass  right outer JOIN  ass.aron as aron WHERE aron.code = (:codeAron)")
    //List<MunicipAron> findByCodeAronAndIdNot(String codeAron);
    //@Query("SELECT ass FROM AronSecteur ass JOIN ass.secteur as secteur WHERE secteur.code LIKE '%(:codeArticle)%' ORDER BY ass.id")
    //Page<MunicipAron> findByCodeMunicipContainingOrderByIdAsc(String codeArticle, Pageable pageable);
    //@Query("SELECT  ass FROM AronSecteur ass JOIN ass.aron as aron  WHERE aron.code LIKE '%(:codeAron)%' ORDER BY ass.id")
    //Page<MunicipAron> findByCodeAronContainingOrderByIdAsc(String codeAron, Pageable pageable);
//	Page<ArticlePrestation> findByTauxPrestationContainingOrderByIdAsc(double tauxPrestation, Pageable pageable);
    // region Find eagerly
    // ==========================================================================
    @Query("SELECT  ass FROM AronSecteur ass JOIN ass.secteur")
    List<MunicipAron> findAllEagerly();

//	@Query("SELECT ap FROM ArticlePrestation ap JOIN FETCH ap.Prestation WHERE ap.tauxPrestation = (:tauxPrestation)")
//	User findByTauxPrestationEagerly(@Param("tauxPrestation") double tauxPrestation);
    //@Query("SELECT  ass FROM AronSecteur ass JOIN ass.secteur WHERE ass.id = (:id)")
    //MunicipAron findByIdEagerly(@Param("id") Long id);
    // ==========================================================================
    // endregion
    @Query("SELECT  ass FROM AronSecteur ass JOIN ass.secteur as secteur where secteur.code = (:codeArticle)")
    Boolean existsByCodeMunicip(String codeArticle);

}
