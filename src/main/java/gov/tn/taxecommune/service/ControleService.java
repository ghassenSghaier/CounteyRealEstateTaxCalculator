package gov.tn.taxecommune.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.tn.taxecommune.entity.Controle;
import gov.tn.taxecommune.repository.ControleRepository;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class ControleService {

	private ControleRepository controleRepository;
	private CacheManager cacheManager;

	public ControleService(ControleRepository controleRepository, CacheManager cacheManager) {
		super();
		this.controleRepository = controleRepository;
		this.cacheManager = cacheManager;
	}

	public ControleRepository getControleRepository() {
		return controleRepository;
	}

	public void setControleRepository(ControleRepository controleRepository) {
		this.controleRepository = controleRepository;
	}

	// region Find methods
	// ==================================================================================
	@Cacheable("cache.allControles")
	public List<Controle> findAll() {
		return controleRepository.findAll();
	}

	@Cacheable(value = "cache.ControleById", key = "#id", unless = "#result == null")
	public Optional<Controle> findById(Long id) {
		return controleRepository.findById(id);
	}

	@Cacheable(value = "cache.ControleByUsername", key = "#username", unless = "#result == null")
	public List<Controle> findByUsername(String username) {
		return controleRepository.findByUsername(username).get();
	}
	
	@Cacheable(value = "cache.ControleByDeclaration", key = "#id", unless = "#result == null")
	public List<Controle> findByDeclaration(String codeDeclaration) {
		return controleRepository.findByCodeDeclaration(codeDeclaration).get();
	}

	public Page<Controle> findByIdPageable(Long id, Pageable pageRequest) {
		Optional<Controle> controle = controleRepository.findById(id);
		List<Controle> users = controle.isPresent() ? Collections.singletonList(controle.get())
				: Collections.emptyList();
		return new PageImpl<>(users, pageRequest, users.size());
	}

	// ==================================================================================
	// endregion

	// region Find by containing
//	@Cacheable(value = "cache.byUsernameContaining")
//	public Page<Controle> findByUserContaining(String username, Pageable pageable) {
//		return controleRepository.findByUserContainingOrderByIdAsc(username, pageable);
//	}

	@Transactional
	@CacheEvict(value = { "cache.allControles", "cache.ControleById", "cache.byUsernameContaining" }, allEntries = true)
	public void save(Controle controle) {
		controleRepository.save(controle);
	}

	@CacheEvict(value = { "cache.allControles", "cache.ControleById", "cache.byUsernameContaining" }, allEntries = true)
	public void delete(Controle controle) {
		controleRepository.delete(controle);
	}
}
