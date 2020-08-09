package gov.tn.taxecommune.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.tn.taxecommune.entity.ArticleTaxation;
import gov.tn.taxecommune.entity.Reclamation;
import gov.tn.taxecommune.repository.ReclamationRepository;
import gov.tn.taxecommune.web.dto.ReclamationUpdateDto;

/**
 * Created by Keno&Kemo on 18.10.2017..
 */

@Service
public class ReclamationService {

	private ReclamationRepository reclamationRepository;
	private ArticleTaxationService articleTaxationService;
	private CacheManager cacheManager;

	public ReclamationService(ReclamationRepository reclamationRepository) {
		super();
		this.reclamationRepository = reclamationRepository;
	}

	public ReclamationRepository getReclamationRepository() {
		return reclamationRepository;
	}

	public void setReclamationRepository(ReclamationRepository reclamationRepository) {
		this.reclamationRepository = reclamationRepository;
	}

	// region find methods
	// ==============================================================================================
	@Cacheable(value = "cache.allReclamations")
	public List<Reclamation> findAll() {
		return reclamationRepository.findAll();
	}

	@Cacheable(value = "cache.allReclamationsPageable")
	public Page<Reclamation> findAllPageable(Pageable pageable) {
		return reclamationRepository.findAll(pageable);
	}

	@Cacheable(value = "cache.allReclamationsUserPageable")
	public Page<Reclamation> findAllPageableForUser(String username, Pageable pageable) {
		return reclamationRepository.findAllForUser(username, pageable);
	}

	@Cacheable(value = "cache.allatReclamationsPageable")
	public List<Reclamation> findAllAtReclamationForUser(String username) {
		return reclamationRepository.findAllAtForUser(username);
	}

	@Cacheable(value = "cache.reclamationByCode", key = "#code", unless = "#result == null")
	public Reclamation findByCodeReclamation(String email) {
		return reclamationRepository.findByCodeReclamation(email);
	}

	@Cacheable(value = "cache.reclamationByUser", key = "#username", unless = "#result == null")
	public List<Reclamation> findByUser(String username) {
		return reclamationRepository.findByCASEagerly(username).get();
	}
		
	public Reclamation findByUserAndArticle(String username,String numeroMunicipal) {
		return reclamationRepository.findByUserAndArticle(username,numeroMunicipal).get();
	}
	
	@Cacheable(value = "cache.reclamationByUser", key = "#username", unless = "#result == null")
	public List<Reclamation> findByArticleAndDate(String username,String CodeArticle) {
		return reclamationRepository.findByCASEagerly(username).get();
	}

//	@Cacheable(value = "cache.reclamationByArticle", key = "#codeArticle", unless = "#result == null")
//	public Page<Reclamation> findByArticleContaining(String codeArticle, Pageable pageable) {
//		return reclamationRepository.findByCodeArticleContainingOrderByIdAsc(codeArticle, pageable);
//	}
//
//	@Cacheable(value = "cache.reclamationByTaxation", key = "#codeTaxation", unless = "#result == null")
//	public Page<Reclamation> findByTaxationContaining(String codeTaxation, Pageable pageable) {
//		return reclamationRepository.findByCodeTaxationContainingOrderByIdAsc(codeTaxation, pageable);
//	}
//
//	@Cacheable(value = "cache.reclamationByMotif", key = "#motifTaxation", unless = "#result == null")
//	public Page<Reclamation> findByMotifContaining(String Motif, Pageable pageable) {
//		return reclamationRepository.findByMotifReclamationContainingOrderByIdAsc(Motif, pageable);
//	}

	@Cacheable(value = "cache.reclamationById", key = "#id", unless = "#result == null")
	public Optional<Reclamation> findById(Long id) {
		return reclamationRepository.findById(id);
	}

	public Page<Reclamation> findByIdPageable(Long id, Pageable pageRequest) {
		Optional<Reclamation> reclamation = reclamationRepository.findById(id);
		List<Reclamation> reclamations = reclamation.isPresent() ? Collections.singletonList(reclamation.get())
				: Collections.emptyList();
		return new PageImpl<>(reclamations, pageRequest, reclamations.size());
	}

	public Page<Reclamation> findByUserPageable(String codeUser, Pageable pageRequest) {
		Optional<List<Reclamation>> reclamation = reclamationRepository.findByCASEagerly(codeUser);
		List<Reclamation> reclamations = reclamation.get();
//				isPresent() ? Collections.singletonList(reclamation.get())
//				: Collections.emptyList();
		return new PageImpl<>(reclamations, pageRequest, reclamations.size());
	}

	public Page<Reclamation> findByArticlePageable(String codeArticle, Pageable pageRequest) {
		Optional<List<Reclamation>> reclamation = reclamationRepository.findByCAS1Eagerly(codeArticle);
		List<Reclamation> reclamations = reclamation.get();
//				isPresent() ? Collections.singletonList(reclamation.get())
//				: Collections.emptyList();
		return new PageImpl<>(reclamations, pageRequest, reclamations.size());
	}

	public Page<Reclamation> findByTaxationPageable(String codeTaxation, Pageable pageRequest) {
		Optional<List<Reclamation>> reclamation = reclamationRepository.findByTaxationEagerly(codeTaxation);
		List<Reclamation> reclamations = reclamation.get();
//				isPresent() ? Collections.singletonList(reclamation.get())
//				: Collections.emptyList();
		return new PageImpl<>(reclamations, pageRequest, reclamations.size());
	}

	public Page<Reclamation> findByMotifPageable(String motif, Pageable pageRequest) {
		Optional<List<Reclamation>> reclamation = reclamationRepository.findByMotifReclamation(motif);
		List<Reclamation> reclamations = reclamation.get();
//				isPresent() ? Collections.singletonList(reclamation.get())
//				: Collections.emptyList();
		return new PageImpl<>(reclamations, pageRequest, reclamations.size());
	}

//	public Reclamation findByCodeReclamationAndIdNot(String email, Long id) {
//		return reclamationRepository.findByCodeReclamationAndIdNot(email, id);
//	}

	// region Find eagerly
	public Reclamation findByIdEagerly(Long id) {
		return reclamationRepository.findByIdEagerly(id);
	}

	@Cacheable(value = "cache.allReclamationsEagerly")
	public List<Reclamation> findAllEagerly() {
		return reclamationRepository.findAllEagerly();
	}
	// endregion

	// region Find by containing
//	@Cacheable(value = "cache.byCodeReclamationContaining")
//	public Page<Reclamation> findByCodeReclamationContaining(String name, Pageable pageable) {
//		return reclamationRepository.findByCodeReclamationContainingOrderByIdAsc(name, pageable);
//	}
//
//	@Cacheable(value = "cache.byMotifReclamationContaining")
//	public Page<Reclamation> findByMotifReclamationContaining(String surname, Pageable pageable) {
//		return reclamationRepository.findByMotifReclamationContainingOrderByIdAsc(surname, pageable);
//	}

	// endregion

	// ==============================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allReclamations", "cache.allReclamationsPageable", "cache.allReclamations",
			"cache.reclamationByMotif", "cache.reclamationByCode", "cache.reclamationById",
			"cache.allReclamationsEagerly", "cache.byCodeReclamationContaining", "cache.byMotifReclamationContaining",
			"cache.allReclamationsUserPageable", "cache.allatReclamationsPageable" }, allEntries = true)
	public void save(Reclamation user) {
		reclamationRepository.save(user);
	}

	@CacheEvict(value = { "cache.allReclamations", "cache.allReclamationsPageable", "cache.allReclamations",
			"cache.reclamationByMotif", "cache.reclamationByCode", "cache.reclamationById",
			"cache.allReclamationsEagerly", "cache.byCodeReclamationContaining", "cache.byMotifReclamationContaining",
			"cache.allReclamationsUserPageable", "cache.allatReclamationsPageable" }, allEntries = true)
	public void deleteById(Long id) {
		reclamationRepository.deleteById(id);
	}

//	public Reclamation createNewReclamation(ReclamationDto recDto) {
//		Reclamation reclamation = new Reclamation();
//		reclamation.setMotifReclamation(recDto.getMotifReclamation());
//		reclamation.setArticleTaxation(recDto.getNumeroMunicipal());		
//		user.setRoles(Collections.singletonList(roleService.findByNom("ROLE_USER")));
//		return user;
//	}
//
//	public User getUpdatedUser(User persistedUser, UserUpdateDto userUpdateDto) {
//		persistedUser.setNom(userUpdateDto.getNom());
//		persistedUser.setPrenom(userUpdateDto.getPrenom());
//		persistedUser.setUsername(userUpdateDto.getUsername());
//		persistedUser.setEmail(userUpdateDto.getEmail());
//		persistedUser.setRoles(getAssignedRolesList(userUpdateDto));
//		persistedUser.setEnabled(userUpdateDto.isEnabled());
//		return persistedUser;
//	}
//
	public List<ArticleTaxation> getAssignedATList(ReclamationUpdateDto reclamationUpdateDto) {
		Map<Long, ArticleTaxation> assignedATMap = new HashMap<>();
		List<ArticleTaxation> ats = new ArrayList<>();
		ats.add(reclamationUpdateDto.getArticleTaxation());
		for (ArticleTaxation role : ats) {
			assignedATMap.put(role.getId(), role);
		}

		List<ArticleTaxation> userRoles = new ArrayList<>();
		List<ArticleTaxation> allRoles = articleTaxationService.findAll();
		for (ArticleTaxation role : allRoles) {
			if (assignedATMap.containsKey(role.getId())) {
				userRoles.add(role);
			} else {
				userRoles.add(null);
			}
		}
		return userRoles;
	}

}
