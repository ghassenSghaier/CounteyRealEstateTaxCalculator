package gov.tn.taxecommune.service;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.tn.taxecommune.entity.Arrondissement;
import gov.tn.taxecommune.repository.ArrondissementRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class ArrondissementService {
	private ArrondissementRepository aronRepository;
	private CacheManager cacheManager;

	public ArrondissementService(ArrondissementRepository aronRepository, CacheManager cacheManager) {
		this.cacheManager = cacheManager;
		this.aronRepository = aronRepository;

	}

	public ArrondissementRepository getAronRepository() {
		return aronRepository;
	}

	public void setAronRepository(ArrondissementRepository aronRepository) {
		this.aronRepository = aronRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allArons")
	public List<Arrondissement> findAll() {
		return aronRepository.findAll();
	}

	@Cacheable(value = "cache.aronByNom", key = "#name", unless = "#result == null")
	public Arrondissement findByNom(String name) {
		return aronRepository.findByNom(name);
	}

	@Cacheable(value = "cache.aronByCode", key = "#code", unless = "#result == null")
	public Optional<Arrondissement> findByCode(String code) {
		return aronRepository.findByCode(code);
	}

	@Cacheable(value = "cache.aronById", key = "#id", unless = "#result == null")
	public Optional<Arrondissement> findById(Long id) {
		return aronRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allarons", "cache.aronByNom", "cache.aronById",
			"cache.aronByCode" }, allEntries = true)
	public void save(Arrondissement aron) {
		aronRepository.save(aron);
	}

	@CacheEvict(value = { "cache.allarons", "cache.aronByNom", "cache.aronById",
			"cache.aronByCode" }, allEntries = true)
	public void delete(Arrondissement aron) {
		aronRepository.delete(aron);
	}

	public boolean checkIfArrondissementNameIsTaken(List<Arrondissement> allArons, Arrondissement aron,
			Arrondissement persistedAron) {
		boolean aronNameAlreadyExists = false;
		for (Arrondissement aron1 : allArons) {
			// Check if the role name is edited and if it is taken
			if (!aron.getNom().equals(persistedAron.getNom()) && aron.getNom().equals(aron1.getNom())) {
				aronNameAlreadyExists = true;
			}
		}
		return aronNameAlreadyExists;
	}

}
