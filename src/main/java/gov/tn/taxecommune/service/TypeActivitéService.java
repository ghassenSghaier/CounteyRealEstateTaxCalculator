package gov.tn.taxecommune.service;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.Role;
import gov.tn.taxecommune.entity.TypeActivité;
import gov.tn.taxecommune.repository.RoleRepository;
import gov.tn.taxecommune.repository.TypeActivitéRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class TypeActivitéService {
	private TypeActivitéRepository typeActivitéRepository;
	private CacheManager cacheManager;

	public TypeActivitéService(TypeActivitéRepository typeActivitéRepository, CacheManager cacheManager) {
		this.typeActivitéRepository = typeActivitéRepository;
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allTypeActivités")
	public List<TypeActivité> findAll() {
		return typeActivitéRepository.findAll();
	}

	@Cacheable(value = "cache.typeActivitéByNom", key = "#name", unless = "#result == null")
	public Optional<TypeActivité> findByNom(String name) {
		return typeActivitéRepository.findByNomType(name);
	}

	@Cacheable(value = "cache.typeActivitéById", key = "#id", unless = "#result == null")
	public Optional<TypeActivité> findById(Long id) {
		return typeActivitéRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allTypeActivités", "cache.typeActivitéByNom",
			"cache.typeActivitéById" }, allEntries = true)
	public void save(TypeActivité role) {
		typeActivitéRepository.save(role);
	}

	public boolean checkIfTypeActivitéNameIsTaken(List<TypeActivité> allTypeActivités, TypeActivité typeactivité,
			TypeActivité persistedTypeActivité) {
		boolean typeActivitéNameAlreadyExists = false;
		for (TypeActivité typeActivité1 : allTypeActivités) {
			// Check if the role name is edited and if it is taken
			if (!typeactivité.getNomType().equals(persistedTypeActivité.getNomType())
					&& typeactivité.getNomType().equals(typeActivité1.getNomType())) {
				typeActivitéNameAlreadyExists = true;
			}
		}
		return typeActivitéNameAlreadyExists;
	}

}
