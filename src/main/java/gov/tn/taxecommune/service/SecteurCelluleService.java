package gov.tn.taxecommune.service;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.SecteurCellule;
import gov.tn.taxecommune.entity.ArticlePrestation;
import gov.tn.taxecommune.entity.ArticleRue;
import gov.tn.taxecommune.entity.ArticleTypeActivité;
import gov.tn.taxecommune.entity.Cellule;
import gov.tn.taxecommune.entity.Prestation;
import gov.tn.taxecommune.entity.Role;
import gov.tn.taxecommune.entity.Secteur;
import gov.tn.taxecommune.repository.SecteurCelluleRepository;
import gov.tn.taxecommune.repository.ArticlePrestationRepository;
import gov.tn.taxecommune.repository.ArticleRueRepository;
import gov.tn.taxecommune.repository.ArticleTypeActivitéRepository;
import gov.tn.taxecommune.repository.PrestationRepository;
import gov.tn.taxecommune.repository.RoleRepository;
import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class SecteurCelluleService {
	private SecteurCelluleRepository secteurCelluleRepository;
	private CacheManager cacheManager;

	public SecteurCelluleService(SecteurCelluleRepository secteurCelluleRepository, CacheManager cacheManager) {
		this.secteurCelluleRepository = secteurCelluleRepository;
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allSecteurCellules")
	public List<SecteurCellule> findAll() {
		return secteurCelluleRepository.findAll();
	}

	@Cacheable("cache.allSecteurCellulesBySecteurAndCellule")
	public Optional<List<SecteurCellule>> findBySecteurAndCellule(Cellule cellule, Secteur secteur) {
		return secteurCelluleRepository.findBySecteurAndCellule(secteur, cellule);
	}

	@Cacheable(value = "cache.SecteurCelluleByCodeCellule", key = "#name", unless = "#result == null")
	public List<SecteurCellule> findByCellule(String name) {
		return secteurCelluleRepository.findByCodeCellule(name);
	}

	@Cacheable(value = "cache.SecteurCelluleByCodeSecteur", key = "#name", unless = "#result == null")
	public List<SecteurCellule> findBySecteur(String name) {
		return secteurCelluleRepository.findByCodeSecteur(name);
	}

	@Cacheable(value = "cache.SecteurCelluleById", key = "#id", unless = "#result == null")
	public Optional<SecteurCellule> findById(Long id) {
		return secteurCelluleRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allSecteurCellules", "cache.SecteurCelluleByCodeSecteur",
			"cache.SecteurCelluleByCodeCellule", "cache.SecteurCelluleById",
			"cache.allSecteurCellulesBySecteurAndCellule" }, allEntries = true)
	public void save(SecteurCellule articleRue) {
		secteurCelluleRepository.save(articleRue);
	}

	public boolean checkIfSecteurCelluleIsTaken(List<SecteurCellule> allmCellules, SecteurCellule mCellule,
			SecteurCellule persistedSecteurCellule) {
		boolean SecteurCelluleAlreadyExists = false;
		for (SecteurCellule SecteurCellule1 : allmCellules) {
			// Check if the role name is edited and if it is taken
			if (!mCellule.getSecteur().getCode().equals(persistedSecteurCellule.getSecteur().getCode())
					&& mCellule.getSecteur().getCode().equals(SecteurCellule1.getSecteur().getCode())
					&& !mCellule.getCellule().getCode().equals(persistedSecteurCellule.getCellule().getCode())
					&& mCellule.getCellule().getCode().equals(SecteurCellule1.getCellule().getCode())
					&& !mCellule.getEncoursCellule().equals(persistedSecteurCellule.getEncoursCellule())
					&& mCellule.getEncoursCellule().equals(SecteurCellule1.getEncoursCellule())
					&& mCellule.getEncoursCellule() == true
					&& !mCellule.getDateCloture().equals(persistedSecteurCellule.getDateCloture())
					&& mCellule.getDateCloture().equals(SecteurCellule1.getDateCloture())
					&& mCellule.getDateCloture() == null) {
				SecteurCelluleAlreadyExists = true;
			}
		}
		return SecteurCelluleAlreadyExists;
	}

}
