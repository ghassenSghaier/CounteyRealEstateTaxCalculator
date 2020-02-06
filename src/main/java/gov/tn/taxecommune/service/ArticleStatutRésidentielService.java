package gov.tn.taxecommune.service;

import java.util.List;
import java.util.Optional;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import gov.tn.taxecommune.entity.ArticleStatutRésidentiel;
import gov.tn.taxecommune.repository.ArticleStatutRésidentielRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class ArticleStatutRésidentielService {
	private ArticleStatutRésidentielRepository articleStatutRésidentielRepository;
	private CacheManager cacheManager;

	public ArticleStatutRésidentielService(ArticleStatutRésidentielRepository articleStatutRésidentielRepository,
			CacheManager cacheManager) {
		this.articleStatutRésidentielRepository = articleStatutRésidentielRepository;
		this.cacheManager = cacheManager;
	}

	public ArticleStatutRésidentielRepository getArticleStatutRésidentielRepository() {
		return articleStatutRésidentielRepository;
	}

	public void setArticleStatutRésidentielRepository(
			ArticleStatutRésidentielRepository articleStatutRésidentielRepository) {
		this.articleStatutRésidentielRepository = articleStatutRésidentielRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allarticleStatutRésidentiels")
	public List<ArticleStatutRésidentiel> findAll() {
		return articleStatutRésidentielRepository.findAll();
	}

	@Cacheable(value = "cache.articleStatutRésidentielByCodeArticle", key = "#name", unless = "#result == null")
	public List<ArticleStatutRésidentiel> findByArticle(String name) {
		return articleStatutRésidentielRepository.findByCodeArticle(name);
	}

	@Cacheable(value = "cache.articleStatutRésidentielByCodeType", key = "#name", unless = "#result == null")
	public List<ArticleStatutRésidentiel> findByStatutRésidentiel(String name) {
		return articleStatutRésidentielRepository.findByCodeStatut(name);
	}

	@Cacheable(value = "cache.articleStatutRésidentielById", key = "#id", unless = "#result == null")
	public Optional<ArticleStatutRésidentiel> findById(Long id) {
		return articleStatutRésidentielRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allArticleTypeActivités", "cache.articleTypeActivitéByCodeArticle",
			"articleTypeActivitéById" }, allEntries = true)
	public void save(ArticleStatutRésidentiel articleStatutRésidentiel) {
		articleStatutRésidentielRepository.save(articleStatutRésidentiel);
	}

	public boolean checkIfarticleStatutRésidentielIsTaken(List<ArticleStatutRésidentiel> allAss,
			ArticleStatutRésidentiel articleStatutRésidentiel,
			ArticleStatutRésidentiel persistedArticleStatutRésidentiel) {
		boolean articleStatutRésidentielAlreadyExists = false;
		for (ArticleStatutRésidentiel articleStatutRésidentiel1 : allAss) {
			// Check if the role name is edited and if it is taken
//			if (!articleStatutRésidentiel.getArticle().getNumeroMunicipal()
//					.equals(persistedArticleStatutRésidentiel.getArticle().getNumeroMunicipal())
//					&& articleStatutRésidentiel1.getStatutRésidentiel().getCodeStatut()
//							.equals(persistedArticleStatutRésidentiel.getStatutRésidentiel().getCodeStatut())) {
//				articleStatutRésidentielAlreadyExists = true;
//			}
		}
		return articleStatutRésidentielAlreadyExists;
	}
}
