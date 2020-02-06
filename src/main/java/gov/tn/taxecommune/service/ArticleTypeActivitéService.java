package gov.tn.taxecommune.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.ArticleTypeActivité;
import gov.tn.taxecommune.entity.Prestation;
import gov.tn.taxecommune.repository.ArticleTypeActivitéRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class ArticleTypeActivitéService {
	private ArticleTypeActivitéRepository articleTypeActivitéRepository;
	private CacheManager cacheManager;

	public ArticleTypeActivitéService(ArticleTypeActivitéRepository articleTypeActivitéRepository,
			CacheManager cacheManager) {
		this.articleTypeActivitéRepository = articleTypeActivitéRepository;
		this.cacheManager = cacheManager;
	}

	public ArticleTypeActivitéRepository getArticleTypeActivitéRepository() {
		return articleTypeActivitéRepository;
	}

	public void setArticleTypeActivitéRepository(ArticleTypeActivitéRepository articleTypeActivitéRepository) {
		this.articleTypeActivitéRepository = articleTypeActivitéRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allArticleTypeActivités")
	public List<ArticleTypeActivité> findAll() {
		return articleTypeActivitéRepository.findAll();
	}

	@Cacheable(value = "cache.articleTypeActivitéByCodeArticle", key = "#name", unless = "#result == null")
	public List<ArticleTypeActivité> findByArticle(long name) {
		return articleTypeActivitéRepository.findByCodeArticle(name);
	}

	@Cacheable(value = "cache.articleTypeActivitéByCodeType", key = "#name", unless = "#result == null")
	public List<ArticleTypeActivité> findByTypeActivité(String name) {
		return articleTypeActivitéRepository.findByCodeType(name);
	}

	@Cacheable(value = "cache.articleTypeActivitéById", key = "#id", unless = "#result == null")
	public Optional<ArticleTypeActivité> findById(Long id) {
		return articleTypeActivitéRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allArticleTypeActivités", "cache.articleTypeActivitéByCodeArticle",
			"cache.articleTypeActivitéByCodeType", "articleTypeActivitéById" }, allEntries = true)
	public void save(ArticleTypeActivité articleTypeActivité) {
		articleTypeActivitéRepository.save(articleTypeActivité);
	}

	public boolean checkIfArticleTypeActivitéNameIsTaken(List<Prestation> allPrestations, Prestation prestation,
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
