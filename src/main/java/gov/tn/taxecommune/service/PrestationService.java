package gov.tn.taxecommune.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import gov.tn.taxecommune.entity.Prestation;
import gov.tn.taxecommune.repository.PrestationRepository;
import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class PrestationService {
	private PrestationRepository prestationRepository;

	public PrestationService(PrestationRepository prestationRepository) {
		this.prestationRepository = prestationRepository;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allPrestations")
	public List<Prestation> findAll() {
		return prestationRepository.findAll();
	}

	@Cacheable(value = "cache.prestationByNom", key = "#name", unless = "#result == null")
	public Optional<Prestation> findByNom(String name) {
		return prestationRepository.findByNomPrestation(name);
	}

	@Cacheable(value = "cache.prestationById", key = "#id", unless = "#result == null")
	public Optional<Prestation> findById(Long id) {
		return prestationRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allPrestations", "cache.prestationByNom",
			"cache.presentationById" }, allEntries = true)
	public void save(Prestation prestation) {
		prestationRepository.save(prestation);
	}

	public boolean checkIfPrestationNameIsTaken(List<Prestation> allPrestations, Prestation prestation,
			Prestation persistedPrestation) {
		boolean prestationNameAlreadyExists = false;
		for (Prestation prestation1 : allPrestations) {
			// Check if the role name is edited and if it is taken
			if (!prestation.getNomPrestation().equals(persistedPrestation.getNomPrestation())
					&& prestation.getNomPrestation().equals(prestation1.getNomPrestation())) {
				prestationNameAlreadyExists = true;
			}
		}
		return prestationNameAlreadyExists;
	}

}
