package gov.tn.taxecommune.service;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gov.tn.taxecommune.entity.Rue;
import gov.tn.taxecommune.repository.RueRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class RueService {

	private RueRepository rueRepository;
	private CacheManager cacheManager;

	public RueService(RueRepository rueRepository, CacheManager cacheManager) {
		this.cacheManager = cacheManager;
		this.rueRepository = rueRepository;

	}

	// region find methods
	// ==============================================================================================
	@Cacheable(value = "cache.allRues")
	public List<Rue> findAll() {
		return rueRepository.findAll();
	}

	@Cacheable(value = "cache.allRuesPageable")
	public Page<Rue> findAllPageable(Pageable pageable) {
		return rueRepository.findAll(pageable);
	}

	@Cacheable(value = "cache.rueByCodeRue", key = "#coderue", unless = "#result == null")
	public Optional<Rue> findByCodeRue(String coderue) {
		return rueRepository.findByCodeRue(coderue);
	}

//	@Cacheable(value = "cache.articleById", key = "#id", unless = "#result == null")
//	public Optional<Article> findById(Long numeroMunicipal) {
//		return articleRepository.findByNumeroMunicipal(numeroMunicipal);
//	}

	public Page<Rue> findByCodeRuePageable(String codeRue, Pageable pageRequest) {
		Optional<Rue> rue = rueRepository.findByCodeRue(codeRue);
		List<Rue> rues = rue.isPresent() ? Collections.singletonList(rue.get()) : Collections.emptyList();
		return new PageImpl<>(rues, pageRequest, rues.size());
	}

	// region Find eagerly
	public Rue findByCodeRueEagerly(String codeRue) {
		return rueRepository.findByCodeRueEagerly(codeRue);
	}

	@Cacheable(value = "cache.allRuesEagerly")
	public List<Rue> findAllEagerly() {
		return rueRepository.findAllEagerly();
	}
	// endregion

	// region Find by containing
	@Cacheable(value = "cache.byClasseRueContaining")
	public Page<Rue> findByClasseRuecontaining(String classeRue, Pageable pageable) {
		return rueRepository.findByClasseRueContainingOrderByCodeRueAsc(classeRue, pageable);
	}

	@Cacheable(value = "cache.byNomRueContaining")
	public Page<Rue> findByNomRueContaining(String nomRue, Pageable pageable) {
		return rueRepository.findByNomRueContainingOrderByCodeRueAsc(nomRue, pageable);
	}

	@Cacheable(value = "cache.byAutreClasseContaining")
	public Page<Rue> findByAutreClasseContaining(String autreClasse, Pageable pageable) {
		return rueRepository.findByAutreClasseRueContainingOrderByCodeRueAsc(autreClasse, pageable);
	}

	// endregion

	// ==============================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allRues", "cache.allRuesPageable", "cache.rueByCodeRue", "cache.allRuesEagerly",
			"cache.byClasseRueContaining", "cache.byNomRueContaining",
			"cache.byAutreClasseContaining" }, allEntries = true)
	public void save(Rue rue) {
		rueRepository.save(rue);
	}

	@CacheEvict(value = { "cache.allRues", "cache.allRuesPageable", "cache.rueByCodeRue", "cache.allRuesEagerly",
			"cache.byClasseRueContaining", "cache.byNomRueContaining",
			"cache.byAutreClasseContaining" }, allEntries = true)
	public void deleteById(Long id) {
		rueRepository.deleteById(id);
	}

	public boolean checkIfCodeRueIsTaken(List<Rue> allRues, Rue rue, Rue persistedRue) {
		boolean rueCodeRueAlreadyExists = false;
		for (Rue rue1 : allRues) {
			// Check if the role name is edited and if it is taken
			if (!rue.getNomRue().equals(persistedRue.getNomRue()) && rue.getNomRue().equals(rue1.getNomRue())) {
				rueCodeRueAlreadyExists = true;
			}
		}
		return rueCodeRueAlreadyExists;
	}

}
