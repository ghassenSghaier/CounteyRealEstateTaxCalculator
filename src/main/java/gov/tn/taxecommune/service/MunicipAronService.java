package gov.tn.taxecommune.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.Arrondissement;
import gov.tn.taxecommune.entity.ArticlePrestation;
import gov.tn.taxecommune.entity.ArticleRue;
import gov.tn.taxecommune.entity.ArticleTypeActivité;
import gov.tn.taxecommune.entity.MunicipAron;
import gov.tn.taxecommune.entity.Municipalité;
import gov.tn.taxecommune.entity.Prestation;
import gov.tn.taxecommune.entity.Role;
import gov.tn.taxecommune.repository.ArticlePrestationRepository;
import gov.tn.taxecommune.repository.ArticleRueRepository;
import gov.tn.taxecommune.repository.ArticleTypeActivitéRepository;
import gov.tn.taxecommune.repository.MunicipAronRepository;
import gov.tn.taxecommune.repository.PrestationRepository;
import gov.tn.taxecommune.repository.RoleRepository;
import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class MunicipAronService {
	private MunicipAronRepository municipAronRepository;

	public MunicipAronService(MunicipAronRepository municipAronRepository) {
		this.municipAronRepository = municipAronRepository;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allMunicipArons")
	public List<MunicipAron> findAll() {
		return municipAronRepository.findAll();
	}
	@Cacheable("cache.allMunicipAronsByMunicipAndAron")
	public Optional<List<MunicipAron>> findByMunicipAndAron(Arrondissement aron, Municipalité municip) {
		return municipAronRepository.findByMunicipAndAron(municip,aron);
	}

	@Cacheable(value = "cache.municipAronByCodeMunicip", key = "#name", unless = "#result == null")
	public List<MunicipAron> findByMunicip(String name) {
		return municipAronRepository.findByCodeMunicip(name);
	}

	@Cacheable(value = "cache.municipAronByCodeAron", key = "#name", unless = "#result == null")
	public List<MunicipAron> findByAron(String name) {
		return municipAronRepository.findByCodeAron(name);
	}

	@Cacheable(value = "cache.municipAronById", key = "#id", unless = "#result == null")
	public Optional<MunicipAron> findById(Long id) {
		return municipAronRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allMunicipArons", "cache.municipAronByCodeMunicip", "cache.municipAronByCodeAron",
			"cache.municipAronById","cache.allMunicipAronsByMunicipAndAron" }, allEntries = true)
	public void save(MunicipAron articleRue) {
		municipAronRepository.save(articleRue);
	}

	public boolean checkIfMunicipAronIsTaken(List<MunicipAron> allmArons, MunicipAron maron,
			MunicipAron persistedMunicipAron) {
		boolean municipAronAlreadyExists = false;
		for (MunicipAron municipAron1 : allmArons) {
			// Check if the role name is edited and if it is taken
			if (!maron.getMunicip().getNom().equals(persistedMunicipAron.getMunicip().getNom())
					&& maron.getMunicip().getNom().equals(municipAron1.getMunicip().getNom())
					&& !maron.getAron().getNom().equals(persistedMunicipAron.getAron().getNom())
					&& maron.getAron().getNom().equals(municipAron1.getAron().getNom())
					&& !maron.getEncoursAron().equals(persistedMunicipAron.getEncoursAron())
					&& maron.getEncoursAron().equals(municipAron1.getEncoursAron()) && maron.getEncoursAron() == true
					&& !maron.getDateCloture().equals(persistedMunicipAron.getDateCloture())
					&& maron.getDateCloture().equals(municipAron1.getDateCloture()) && maron.getDateCloture() == null) {
				municipAronAlreadyExists = true;
			}
		}
		return municipAronAlreadyExists;
	}

}
