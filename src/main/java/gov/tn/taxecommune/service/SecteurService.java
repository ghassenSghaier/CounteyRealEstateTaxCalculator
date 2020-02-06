package gov.tn.taxecommune.service;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.tn.taxecommune.entity.Secteur;
import gov.tn.taxecommune.repository.SecteurRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class SecteurService {
	private SecteurRepository SecteurRepository;
	private CacheManager cacheManager;

	public SecteurService(SecteurRepository SecteurRepository, CacheManager cacheManager) {
		this.SecteurRepository = SecteurRepository;
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allSecteurs")
	public List<Secteur> findAll() {
		return SecteurRepository.findAll();
	}

	@Cacheable(value = "cache.SecteurByCode", key = "#name", unless = "#result == null")
	public Optional<Secteur> findByCode(String name) {
		return SecteurRepository.findByCode(name);
	}

	@Cacheable(value = "cache.SecteurById", key = "#id", unless = "#result == null")
	public Optional<Secteur> findById(Long id) {
		return SecteurRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allSecteurs", "cache.SecteurByCode", "cache.SecteurById" }, allEntries = true)
	public void save(Secteur Secteur) {
		SecteurRepository.save(Secteur);
	}

	@CacheEvict(value = { "cache.allSecteurs", "cache.SecteurByCode", "cache.SecteurById" }, allEntries = true)
	public void delete(Secteur Secteur) {
		SecteurRepository.delete(Secteur);
	}

	public boolean checkIfSecteurNameIsTaken(List<Secteur> allSecteurs, Secteur Secteur, Secteur persistedSecteur) {
		boolean SecteurNameAlreadyExists = false;
		for (Secteur Secteur1 : allSecteurs) {
			// Check if the Secteur name is edited and if it is taken
			if (!Secteur.getCode().equals(persistedSecteur.getCode()) && Secteur.getCode().equals(Secteur1.getCode())) {
				SecteurNameAlreadyExists = true;
			}
		}
		return SecteurNameAlreadyExists;
	}

}
