package gov.tn.taxecommune.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.AronSecteur;
import gov.tn.taxecommune.entity.Arrondissement;
import gov.tn.taxecommune.entity.Secteur;
import gov.tn.taxecommune.repository.AronSecteurRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class AronSecteurService {
	private AronSecteurRepository aronSecteurRepository;
	private CacheManager cacheManager;

	public AronSecteurService(AronSecteurRepository aronSecteurRepository, CacheManager cacheManager) {
		super();
		this.aronSecteurRepository = aronSecteurRepository;
		this.cacheManager = cacheManager;
	}

	public AronSecteurRepository getAronSecteurRepository() {
		return aronSecteurRepository;
	}

	public void setAronSecteurRepository(AronSecteurRepository aronSecteurRepository) {
		this.aronSecteurRepository = aronSecteurRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allAronSecteurs")
	public List<AronSecteur> findAll() {
		return aronSecteurRepository.findAll();
	}

	@Cacheable("cache.allAronSecteurByAronAndSecteur")
	public Optional<List<AronSecteur>> findByAronAndSecteur(Arrondissement aron, Secteur secteur) {
		return aronSecteurRepository.findByAronAndSecteur(aron, secteur);
	}

	@Cacheable(value = "cache.aronSecteurByCodeAron", key = "#name", unless = "#result == null")
	public List<AronSecteur> findByAron(String name) {
		return aronSecteurRepository.findByCodeAron(name);
	}

	@Cacheable(value = "cache.aronSecteurByCodeSecteur", key = "#name", unless = "#result == null")
	public List<AronSecteur> findBySecteur(String name) {
		return aronSecteurRepository.findByCodeSecteur(name);
	}

	@Cacheable(value = "cache.aronSecteurById", key = "#id", unless = "#result == null")
	public Optional<AronSecteur> findById(Long id) {
		return aronSecteurRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allAronSecteurs", "cache.aronSecteurByCodeSecteur", "cache.aronSecteurByCodeAron",
			"cache.aronSecteurById", "cache.allAronSecteurByAronAndSecteur" }, allEntries = true)
	public void save(AronSecteur articleRue) {
		aronSecteurRepository.save(articleRue);
	}

	public boolean checkIfAronSecteurIsTaken(List<AronSecteur> allmArons, AronSecteur maron,
			AronSecteur persistedaronSecteur) {
		boolean aronSecteurAlreadyExists = false;
		for (AronSecteur aronSecteur1 : allmArons) {
			// Check if the role name is edited and if it is taken
			if (!maron.getSecteur().getCode().equals(persistedaronSecteur.getSecteur().getCode())
					&& maron.getSecteur().getCode().equals(aronSecteur1.getSecteur().getCode())
					&& !maron.getAron().getNom().equals(persistedaronSecteur.getAron().getNom())
					&& maron.getAron().getNom().equals(aronSecteur1.getAron().getNom())
					&& !maron.getEncoursSecteur().equals(persistedaronSecteur.getEncoursSecteur())
					&& maron.getEncoursSecteur().equals(aronSecteur1.getEncoursSecteur())
					&& maron.getEncoursSecteur() == true
					&& !maron.getDateCloture().equals(persistedaronSecteur.getDateCloture())
					&& maron.getDateCloture().equals(aronSecteur1.getDateCloture()) && maron.getDateCloture() == null) {
				aronSecteurAlreadyExists = true;
			}
		}
		return aronSecteurAlreadyExists;
	}

}
