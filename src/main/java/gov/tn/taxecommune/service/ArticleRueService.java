package gov.tn.taxecommune.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.ArticleRue;
import gov.tn.taxecommune.repository.ArticleRueRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class ArticleRueService {
	private ArticleRueRepository articleRueRepository;
	private CacheManager cacheManager;

	public ArticleRueService(ArticleRueRepository articleRueRepository, CacheManager cacheManager) {
		super();
		this.articleRueRepository = articleRueRepository;
		this.cacheManager = cacheManager;
	}

	public ArticleRueRepository getArticleRueRepository() {
		return articleRueRepository;
	}

	public void setArticleRueRepository(ArticleRueRepository articleRueRepository) {
		this.articleRueRepository = articleRueRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allArticleRues")
	public List<ArticleRue> findAll() {
		return articleRueRepository.findAll();
	}

	@Cacheable(value = "cache.articleRueByCodeArticle", key = "#name", unless = "#result == null")
	public List<ArticleRue> findByArticle(String name) {
		return articleRueRepository.findByCodeArticle(name);
	}

	@Cacheable(value = "cache.articleRueByCodePrestation", key = "#name", unless = "#result == null")
	public List<ArticleRue> findByRue(String name) {
		return articleRueRepository.findByCodeRue(name);
	}

	@Cacheable(value = "cache.articleRueById", key = "#id", unless = "#result == null")
	public Optional<ArticleRue> findById(Long id) {
		return articleRueRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allArticleRues", "cache.articleRueByCodeArticle", "cache.articleRueByCodeRue",
			"cache.articleRueById" }, allEntries = true)
	public void save(ArticleRue articleRue) {
		articleRueRepository.save(articleRue);
	}

	public boolean checkIfArticleRueIsTaken(List<ArticleRue> allaRues, ArticleRue arue,
			ArticleRue persistedArticleRue) {
		boolean articleRueAlreadyExists = false;
		for (ArticleRue articleRue1 : allaRues) {
			// Check if the role name is edited and if it is taken
			if (!arue.getRue().getNomRue().equals(persistedArticleRue.getRue().getNomRue())
					&& arue.getRue().getNomRue().equals(articleRue1.getRue().getNomRue())) {
				articleRueAlreadyExists = true;
			}
		}
		return articleRueAlreadyExists;
	}

}
