package gov.tn.taxecommune.service;

import java.util.List;
import java.util.Optional;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import gov.tn.taxecommune.entity.StatutRésidentiel;
import gov.tn.taxecommune.repository.StatutRésidentielRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class StatutRésidentielService {
    private StatutRésidentielRepository statutRepository;
    private CacheManager cacheManager;

    public StatutRésidentielService(StatutRésidentielRepository statutRepository, CacheManager cacheManager) {
        this.statutRepository = statutRepository;
        this.cacheManager=cacheManager;
    }

    //region Find methods
    //==================================================================================
    @Cacheable("cache.allStatus")
    public List<StatutRésidentiel> findAll() {
        return statutRepository.findAll();
    }

    @Cacheable(value = "cache.statutByNom", key = "#name", unless = "#result == null")
    public Optional<StatutRésidentiel> findByNom(String name) {
        return statutRepository.findByNomStatut(name);
    }

    @Cacheable(value = "cache.statutById", key = "#id", unless = "#result == null")
    public Optional<StatutRésidentiel> findById(Long id) {
        return statutRepository.findById(id);
    }
    //==================================================================================
    //endregion

    @CacheEvict(value = {"cache.allStatus" , "cache.statutByNom", "cache.statutById"}, allEntries = true)
    public void save(StatutRésidentiel statut) {
        statutRepository.save(statut);
    }

    public boolean checkIfStatutNameIsTaken(List<StatutRésidentiel> allStatus, StatutRésidentiel statut, StatutRésidentiel persistedStatut) {
        boolean statutNameAlreadyExists = false;
        for (StatutRésidentiel statut1 : allStatus) {
            //Check if the role name is edited and if it is taken
            if (!statut.getNomStatut().equals(persistedStatut.getNomStatut())
                    && statut.getNomStatut().equals(statut1.getNomStatut())) {
                statutNameAlreadyExists = true;
            }
        }
        return statutNameAlreadyExists;
    }


}
