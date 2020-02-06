package gov.tn.taxecommune.service;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.tn.taxecommune.entity.Cellule;
import gov.tn.taxecommune.repository.CelluleRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class CelluleService {
	private CelluleRepository CelluleRepository;
	private CacheManager cacheManager;

	public CelluleService(CelluleRepository CelluleRepository, CacheManager cacheManager) {
		this.CelluleRepository = CelluleRepository;
		this.cacheManager = cacheManager;
	}

	public CelluleRepository getCelluleRepository() {
		return CelluleRepository;
	}

	public void setCelluleRepository(CelluleRepository celluleRepository) {
		CelluleRepository = celluleRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allCellules")
	public List<Cellule> findAll() {
		return CelluleRepository.findAll();
	}

	@Cacheable(value = "cache.CelluleByCode", key = "#name", unless = "#result == null")
	public Optional<Cellule> findByCode(String name) {
		return CelluleRepository.findByCode(name);
	}

	@Cacheable(value = "cache.CelluleById", key = "#id", unless = "#result == null")
	public Optional<Cellule> findById(Long id) {
		return CelluleRepository.findById(id);
	}
	@Cacheable(value = "cache.CelluleByCodeRue", key = "#codeRue", unless = "#result == null")
	public Optional<Cellule> findByCodeRue(String codeRue) {
		return CelluleRepository.findByCodeRue(codeRue);
	}
	// ==================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allCellules", "cache.CelluleByCode", "cache.CelluleById","cache.CelluleByCodeRue"}, allEntries = true)
	public void save(Cellule Cellule) {
		CelluleRepository.save(Cellule);
	}

	@CacheEvict(value = { "cache.allCellules", "cache.CelluleByCode", "cache.CelluleById","cache.CelluleByCodeRue"}, allEntries = true)
	public void delete(Cellule Cellule) {
		CelluleRepository.delete(Cellule);
	}

	public boolean checkIfCelluleCodeIsTaken(List<Cellule> allCellules, Cellule Cellule, Cellule persistedCellule) {
		boolean CelluleNameAlreadyExists = false;
		for (Cellule Cellule1 : allCellules) {
			// Check if the Cellule name is edited and if it is taken
			if (!Cellule.getCode().equals(persistedCellule.getCode()) && Cellule.getCode().equals(Cellule1.getCode())) {
				CelluleNameAlreadyExists = true;
			}
		}
		return CelluleNameAlreadyExists;
	}

}
