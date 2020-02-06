package gov.tn.taxecommune.service;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.tn.taxecommune.entity.Municipalité;
import gov.tn.taxecommune.repository.MunicipRepository;
import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class MunicipService {
	private MunicipRepository municipRepository;
	private CacheManager cacheManager;

	public MunicipService(MunicipRepository municipRepository, CacheManager cacheManager) {
		this.municipRepository = municipRepository;
		this.cacheManager = cacheManager;

	}

	public MunicipRepository getMunicipRepository() {
		return municipRepository;
	}

	public void setMunicipRepository(MunicipRepository municipRepository) {
		this.municipRepository = municipRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allMunicips")
	public List<Municipalité> findAll() {
		return municipRepository.findAll();
	}

	@Cacheable(value = "cache.municipByNom", key = "#name", unless = "#result == null")
	public Municipalité findByNom(String name) {
		return municipRepository.findByNom(name);
	}

	@Cacheable(value = "cache.municipByCode", key = "#code", unless = "#result == null")
	public Optional<Municipalité> findByCode(String code) {
		return municipRepository.findByCodeMunicip(code);
	}

	@Cacheable(value = "cache.municipById", key = "#id", unless = "#result == null")
	public Optional<Municipalité> findById(Long id) {
		return municipRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allMunicips", "cache.municipByNom", "cache.municipById",
			"cache.municipByCode" }, allEntries = true)
	public void save(Municipalité municip) {
		municipRepository.save(municip);
	}

	@CacheEvict(value = { "cache.allMunicips", "cache.municipByNom", "cache.municipById",
			"cache.municipByCode" }, allEntries = true)
	public void delete(Municipalité municip) {
		municipRepository.delete(municip);
	}

	public boolean checkIfMunicipNameIsTaken(List<Municipalité> allMunicips, Municipalité municip,
			Municipalité persistedMunicipalité) {
		boolean municipNameAlreadyExists = false;
		for (Municipalité municip1 : allMunicips) {
			// Check if the role name is edited and if it is taken
			if (!municip.getNom().equals(persistedMunicipalité.getNom())
					&& municip1.getNom().equals(municip1.getNom())) {
				municipNameAlreadyExists = true;
			}
		}
		return municipNameAlreadyExists;
	}

}
