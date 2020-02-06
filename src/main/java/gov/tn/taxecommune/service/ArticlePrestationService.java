package gov.tn.taxecommune.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.ArticlePrestation;
import gov.tn.taxecommune.repository.ArticlePrestationRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class ArticlePrestationService {
	private ArticlePrestationRepository articlePrestationRepository;
	private CacheManager cacheManager;

	public ArticlePrestationService(ArticlePrestationRepository articlePrestationRepository,
			CacheManager cacheManager) {
		super();
		this.articlePrestationRepository = articlePrestationRepository;
		this.cacheManager = cacheManager;
	}

	public ArticlePrestationRepository getArticlePrestationRepository() {
		return articlePrestationRepository;
	}

	public void setArticlePrestationRepository(ArticlePrestationRepository articlePrestationRepository) {
		this.articlePrestationRepository = articlePrestationRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allArticlePrestations")
	public List<ArticlePrestation> findAll() {
		return articlePrestationRepository.findAll();
	}

	@Cacheable(value = "cache.articlePrestationByCodeArticle", key = "#name", unless = "#result == null")
	public List<ArticlePrestation> findByArticle(String name) {
		return articlePrestationRepository.findByCodeArticle(name);
	}

	@Cacheable(value = "cache.articlePrestationByCodePrestation", key = "#name", unless = "#result == null")
	public List<ArticlePrestation> findByPrestation(String name) {
		return articlePrestationRepository.findByCodePrestation(name);
	}

	@Cacheable(value = "cache.articlePrestationById", key = "#id", unless = "#result == null")
	public Optional<ArticlePrestation> findById(Long id) {
		return articlePrestationRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allArticlePrestations", "cache.articlePrestationByCodeArticle",
			"cache.articlePrestationByCodePrestation", "cache.articlePrestationById" }, allEntries = true)
	public void save(ArticlePrestation articlePrestation) {
		articlePrestationRepository.save(articlePrestation);
	}

	public boolean checkIfArticlePrestationIsTaken(List<ArticlePrestation> allaPrestations,
			ArticlePrestation aprestation, ArticlePrestation persistedArticlePrestation) {
		boolean articlePrestationAlreadyExists = false;
		for (ArticlePrestation articlePrestation1 : allaPrestations) {
			// Check if the role name is edited and if it is taken
			if (!aprestation.getPrestation().getNomPrestation()
					.equals(persistedArticlePrestation.getPrestation().getNomPrestation())
					&& aprestation.getPrestation().getNomPrestation()
							.equals(articlePrestation1.getPrestation().getNomPrestation())) {
				articlePrestationAlreadyExists = true;
			}
		}
		return articlePrestationAlreadyExists;
	}

}
