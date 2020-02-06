package gov.tn.taxecommune.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.Densite;
import gov.tn.taxecommune.repository.DensitéRepository;
import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class DensitéService {
	private DensitéRepository densitéRepository;

	public DensitéService(DensitéRepository densitéRepository) {
		this.densitéRepository = densitéRepository;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allDensites")
	public List<Densite> findAll() {
		return densitéRepository.findAll();
	}

	@Cacheable(value = "cache.densitéByNom", key = "#name", unless = "#result == null")
	public Optional<Densite> findByNom(String name) {
		return densitéRepository.findByNomDensité(name);
	}

	@Cacheable(value = "cache.densitéById", key = "#id", unless = "#result == null")
	public Optional<Densite> findById(Long id) {
		return densitéRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allDensites", "cache.densitéByNom", "cache.densitéById" }, allEntries = true)
	public void save(Densite densité) {
		densitéRepository.save(densité);
	}

	public boolean checkIfDensitéNameIsTaken(List<Densite> allDensités, Densite densité, Densite persistedDensité) {
		boolean densitéNameAlreadyExists = false;
		for (Densite densité1 : allDensités) {
			// Check if the role name is edited and if it is taken
			if (!densité.getNomDensité().equals(persistedDensité.getNomDensité())
					&& densité.getNomDensité().equals(densité1.getNomDensité())) {
				densitéNameAlreadyExists = true;
			}
		}
		return densitéNameAlreadyExists;
	}

}
