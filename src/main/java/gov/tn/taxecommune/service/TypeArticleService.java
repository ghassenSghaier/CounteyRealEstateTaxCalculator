package gov.tn.taxecommune.service;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.TypeArticle;
import gov.tn.taxecommune.repository.TypeArticleRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class TypeArticleService {
	private TypeArticleRepository typeArticleRepository;
	private CacheManager cacheManager;

	public TypeArticleService(TypeArticleRepository typeArticleRepository,CacheManager cacheManager) {
		this.typeArticleRepository = typeArticleRepository;
		this.cacheManager=cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allTypeArticles")
	public List<TypeArticle> findAll() {
		return typeArticleRepository.findAll();
	}

	@Cacheable(value = "cache.TypeArticleByNom", key = "#name", unless = "#result == null")
	public Optional<TypeArticle> findByNom(String name) {
		return typeArticleRepository.findByNomType(name);
	}

	@Cacheable(value = "cache.TypeArticleById", key = "#id", unless = "#result == null")
	public Optional<TypeArticle> findById(Long id) {
		return typeArticleRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allTypeArticles", "cache.TypeArticleByNom",
			"cache.TypeArticleById" }, allEntries = true)
	public void save(TypeArticle TypeArticle) {
		typeArticleRepository.save(TypeArticle);
	}

	public boolean checkIfTypeArticleNameIsTaken(List<TypeArticle> allTypeArticles, TypeArticle TypeArticle,
			TypeArticle persistedTypeArticle) {
		boolean TypeArticleNameAlreadyExists = false;
		for (TypeArticle TypeArticle1 : allTypeArticles) {
			// Check if the TypeArticle name is edited and if it is taken
			if (!TypeArticle.getNomType().equals(persistedTypeArticle.getNomType())
					&& TypeArticle.getNomType().equals(TypeArticle1.getNomType())) {
				TypeArticleNameAlreadyExists = true;
			}
		}
		return TypeArticleNameAlreadyExists;
	}

}
