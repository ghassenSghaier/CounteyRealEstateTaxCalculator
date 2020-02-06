package gov.tn.taxecommune.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gov.tn.taxecommune.entity.Role;
import gov.tn.taxecommune.entity.Vocation;
import gov.tn.taxecommune.entity.Vocation;
import gov.tn.taxecommune.repository.VocationRepository;
import gov.tn.taxecommune.repository.VocationRepository;
import java.util.*;
import javax.annotation.PostConstruct;

/**
 * Created by Keno&Kemo on 18.10.2017..
 */

@Service
public class VocationService {

	private VocationRepository vocationRepository;

	public VocationService(VocationRepository vocationRepository) {
		this.vocationRepository = vocationRepository;
	}

	// region find methods
	// ==============================================================================================
	@Cacheable(value = "cache.allVocations")
	public List<Vocation> findAll() {
		return vocationRepository.findAll();
	}

	@Cacheable(value = "cache.allVocationsPageable")
	public Page<Vocation> findAllPageable(Pageable pageable) {
		return vocationRepository.findAll(pageable);
	}

	@Cacheable(value = "cache.VocationByNomVocation", key = "#email", unless = "#result == null")
	public Vocation findByNomVocation(String email) {
		return vocationRepository.findByNomVocation(email);
	}
	@Cacheable(value = "cache.VocationByCodeVocation", key = "#email", unless = "#result == null")
	public Optional<Vocation> findByCodeVocation(String email) {
		return vocationRepository.findByCodeVocation(email);
	}

	@Cacheable(value = "cache.VocationById", key = "#id", unless = "#result == null")
	public Optional<Vocation> findById(Long id) {
		return vocationRepository.findById(id);
	}

	public Page<Vocation> findByIdPageable(Long id, Pageable pageRequest) {
		Optional<Vocation> Vocation = vocationRepository.findById(id);
		List<Vocation> Vocations = Vocation.isPresent() ? Collections.singletonList(Vocation.get())
				: Collections.emptyList();
		return new PageImpl<>(Vocations, pageRequest, Vocations.size());
	}

	//public Vocation findByNomVocationAndIdNot(String email, Long id) {
	//	return vocationRepository.findByNomVocationAndIdNot(email, id);
	//}

	//public Vocation findByCodeVocationAndIdNot(String Vocationname, Long id) {
	//	return vocationRepository.findByCodeVocationAndIdNot(Vocationname, id);
	//}

	// region Find eagerly
//	public Vocation findByIdEagerly(Long id) {
//		return vocationRepository.findByIdEagerly(id);
//	}
//
//	@Cacheable(value = "cache.allVocationsEagerly")
//	public List<Vocation> findAllEagerly() {
//		return vocationRepository.findAllEagerly();
//	}
	// endregion

	// region Find by containing
//	@Cacheable(value = "cache.byNomVocationContaining")
//	public Page<Vocation> findByNomVocationContaining(String name, Pageable pageable) {
//		return vocationRepository.findByNomVocationContainingOrderByIdAsc(name, pageable);
//	}
//
//	@Cacheable(value = "cache.byCodeVocationContaining")
//	public Page<Vocation> findByCodeVocationContaining(String Vocationname, Pageable pageable) {
//		return vocationRepository.findByCodeVocationContainingOrderByIdAsc(Vocationname, pageable);
//	}

	// endregion

	// ==============================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allVocationsPageable", "cache.allVocations", "cache.VocationByCodeVocation",
			"cache.VocationById", "cache.allVocationsEagerly", "cache.byNomVocationContaining",
			"cache.byCodeVocationContaining","cache.VocationByNomVocation"}, allEntries = true)
	public void save(Vocation Vocation) {
		vocationRepository.save(Vocation);
	}

	@CacheEvict(value = { "cache.allVocationsPageable", "cache.allVocations", "cache.VocationByCodeVocation",
			"cache.VocationById", "cache.allVocationsEagerly", "cache.byNomVocationContaining",
			"cache.byCodeVocationContaining","cache.VocationByNomVocation"}, allEntries = true)
	public void deleteById(Long id) {
		vocationRepository.deleteById(id);
	}
}
