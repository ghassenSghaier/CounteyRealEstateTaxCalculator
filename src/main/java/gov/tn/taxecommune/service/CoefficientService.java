package gov.tn.taxecommune.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.tn.taxecommune.entity.Parametre;
import gov.tn.taxecommune.repository.ParamRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class CoefficientService {
	private ParamRepository coefRepository;
	private CacheManager cacheManager;

	public CoefficientService(ParamRepository coefRepository, CacheManager cacheManager) {
		this.coefRepository = coefRepository;
		this.cacheManager = cacheManager;
	}

	public ParamRepository getCoefRepository() {
		return coefRepository;
	}

	public void setCoefRepository(ParamRepository coefRepository) {
		this.coefRepository = coefRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allCoefs")
	public List<Parametre> findAll() {
		return coefRepository.findAll();
	}

	@Cacheable(value = "cache.CoefByCode", key = "#name", unless = "#result == null")
	public Optional<Parametre> findByCode(String name) {
		return coefRepository.findByCodeParam(name);
	}

	@Cacheable(value = "cache.CoefById", key = "#id", unless = "#result == null")
	public Optional<Parametre> findById(Long id) {
		return coefRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allCoefs", "cache.CoefByCode", "cache.CoefById" }, allEntries = true)
	public void save(Parametre coef) {
		coefRepository.save(coef);
	}

	@CacheEvict(value = { "cache.allCoefs", "cache.CoefByCode", "cache.CoefById" }, allEntries = true)
	public void delete(Parametre coef) {
		coefRepository.delete(coef);
	}

	public boolean checkIfCoefficientCodeIsTaken(List<Parametre> allCoefs, Parametre coefficient,
			Parametre persistedCoefficient) {
		boolean CoefficientCodeAlreadyExists = false;
		for (Parametre Coef1 : allCoefs) {
			// Check if the Cellule name is edited and if it is taken
			if (!coefficient.getCodeParam().equals(persistedCoefficient.getCodeParam())
					&& coefficient.getCodeParam().equals(Coef1.getCodeParam())) {
				CoefficientCodeAlreadyExists = true;
			}
		}
		return CoefficientCodeAlreadyExists;
	}

}
