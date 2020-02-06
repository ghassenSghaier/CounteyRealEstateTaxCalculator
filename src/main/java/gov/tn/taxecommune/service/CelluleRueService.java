package gov.tn.taxecommune.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.Cellule;
import gov.tn.taxecommune.entity.CelluleRue;
import gov.tn.taxecommune.entity.Rue;
import gov.tn.taxecommune.repository.CelluleRueRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class CelluleRueService {
	private CelluleRueRepository celluleRueRepository;
	private CacheManager cacheManager;

	public CelluleRueService(CelluleRueRepository celluleRueRepository, CacheManager cacheManager) {
		this.celluleRueRepository = celluleRueRepository;
		this.cacheManager = cacheManager;
	}

	public CelluleRueRepository getCelluleRueRepository() {
		return celluleRueRepository;
	}

	public void setCelluleRueRepository(CelluleRueRepository celluleRueRepository) {
		this.celluleRueRepository = celluleRueRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allCelluleRues")
	public List<CelluleRue> findAll() {
		return celluleRueRepository.findAll();
	}

	@Cacheable("cache.allCelluleRuesByCelluleANDRue")
	public Optional<List<CelluleRue>> findByCelluleANDRue(Cellule cellule, Rue rue) {
		return celluleRueRepository.findByCelluleAndRue(cellule, rue);
	}

	@Cacheable(value = "cache.CelluleRueByCodeCellule", key = "#name", unless = "#result == null")
	public List<CelluleRue> findByCellule(String name) {
		return celluleRueRepository.findByCodeCellule(name);
	}

	@Cacheable(value = "cache.CelluleRueByCodeRue", key = "#name", unless = "#result == null")
	public List<CelluleRue> findByRue(String name) {
		return celluleRueRepository.findByCodeRue(name);
	}

	@Cacheable(value = "cache.CelluleRueById", key = "#id", unless = "#result == null")
	public Optional<CelluleRue> findById(Long id) {
		return celluleRueRepository.findById(id);
	}
	// ==================================================================================
	// endregion

	@CacheEvict(value = { "cache.allCelluleRues", "cache.CelluleRueByCodeRue", "cache.CelluleRueByCodeCellule",
			"cache.CelluleRueById", "allCelluleRuesByCelluleANDRue" }, allEntries = true)
	public void save(CelluleRue articleRue) {
		celluleRueRepository.save(articleRue);
	}

	public boolean checkIfCelluleRueIsTaken(List<CelluleRue> allmCellules, CelluleRue mCellule,
			CelluleRue persistedCelluleRue) {
		boolean CelluleRueAlreadyExists = false;
		for (CelluleRue CelluleRue1 : allmCellules) {
			// Check if the role name is edited and if it is taken
			if (!mCellule.getRue().getCodeRue().equals(persistedCelluleRue.getRue().getCodeRue())
					&& mCellule.getRue().getCodeRue().equals(CelluleRue1.getRue().getCodeRue())
					&& !mCellule.getCellule().getCode().equals(persistedCelluleRue.getCellule().getCode())
					&& mCellule.getCellule().getCode().equals(CelluleRue1.getCellule().getCode())
					&& !mCellule.getEncoursRue().equals(persistedCelluleRue.getEncoursRue())
					&& mCellule.getEncoursRue().equals(CelluleRue1.getEncoursRue()) && mCellule.getEncoursRue() == true
					&& !mCellule.getDateCloture().equals(persistedCelluleRue.getDateCloture())
					&& mCellule.getDateCloture().equals(CelluleRue1.getDateCloture())
					&& mCellule.getDateCloture() == null) {
				CelluleRueAlreadyExists = true;
			}
		}
		return CelluleRueAlreadyExists;
	}

}
