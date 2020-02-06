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

import gov.tn.taxecommune.entity.ArticleTaxation;
import gov.tn.taxecommune.repository.ArticleTaxationRepository;

/**
 * Created by Keno&Kemo on 18.10.2017..
 */

@Service
public class ArticleTaxationService {

	private ArticleTaxationRepository atRepository;
	private CacheManager cacheManager;

	public ArticleTaxationService(ArticleTaxationRepository atRepository, CacheManager cacheManager) {
		super();
		this.atRepository = atRepository;
		this.cacheManager = cacheManager;
	}

	public ArticleTaxationRepository getAtRepository() {
		return atRepository;
	}

	public void setAtRepository(ArticleTaxationRepository atRepository) {
		this.atRepository = atRepository;
	}

	// region find methods
	// ==============================================================================================
	@Cacheable(value = "cache.allArticleTaxations")
	public List<ArticleTaxation> findAll() {
		return atRepository.findAll();
	}

	@Cacheable(value = "cache.allArticleTaxationsPageable")
	public Page<ArticleTaxation> findAllPageable(Pageable pageable) {
		return atRepository.findAll(pageable);
	}
	
	@Cacheable(value = "cache.allatReclamations")
	public List<ArticleTaxation> findAllAtReclamationForUser(String username) {
		return atRepository.findAllAtForUser(username);
	}
	
	@Cacheable(value = "cache.articleTaxationById", key = "#id", unless = "#result == null")
	public Optional<ArticleTaxation> findById(Long id) {
		return atRepository.findById(id);
	}
	
	@Cacheable(value = "cache.articleTaxationByTaxationAndArticle", key = "#id", unless = "#result == null")
	public Optional<ArticleTaxation> findByTaxationAndArticle(Long id) {
		return atRepository.findById(id);
	}

	public Page<ArticleTaxation> findByIdPageable(Long id, Pageable pageRequest) {
		Optional<ArticleTaxation> at = atRepository.findById(id);
		List<ArticleTaxation> ats = at.isPresent() ? Collections.singletonList(at.get())
				: Collections.emptyList();
		return new PageImpl<>(ats, pageRequest, ats.size());
	}

	
	public List<ArticleTaxation> findByMontantPayment(double montant) {
		return atRepository.findByMontantPayment(montant);
	}

	// region Find eagerly
	//public ArticleTaxation findByIdEagerly(Long id) {
	//	return atRepository.findByIdEagerly(id);
	//}

	@Cacheable(value = "cache.allArticleTaxationsEagerly")
	public List<ArticleTaxation> findAllEagerly() {
		return atRepository.findAllEagerly();
	}
	// endregion

	// region Find by containing
	//@Cacheable(value = "cache.byMontantPaymentFnahContaining")
	//public Page<ArticleTaxation> findByMontantPaymentFnahContaining(double montant, Pageable pageable) {
	//	return atRepository.findByMontantPaymentFNAHContainingOrderByIdAsc(montant, pageable);
	//}

	//@Cacheable(value = "cache.byMontantPaymentContaining")
	//public Page<ArticleTaxation> findByMontantPaymentContaining(double montant, Pageable pageable) {
	//	return atRepository.findByMontantPaymentContainingOrderByIdAsc(montant, pageable);
	//}

	// endregion

	// ==============================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allArticleTaxationsPageable", "cache.allArticleTaxations",
			"cache.ArticleTaxationById", "cache.allArticleTaxationsEagerly", "cache.byMontantPaymentFnahContaining",
			"cache.byMontantPaymentContaining" }, allEntries = true)
	public void save(ArticleTaxation user) {
		atRepository.save(user);
	}

	@CacheEvict(value = { "cache.allArticleTaxationsPageable", "cache.allArticleTaxations",
			"cache.ArticleTaxationById", "cache.allArticleTaxationsEagerly", "cache.byMontantPaymentFnahContaining",
			"cache.byMontantPaymentContaining" }, allEntries = true)
	public void deleteById(Long id) {
		atRepository.deleteById(id);
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
//	public List<Role> getAssignedRolesList(UserUpdateDto userUpdateDto) {
//		Map<Long, Role> assignedRoleMap = new HashMap<>();
//		List<Role> roles = userUpdateDto.getRoles();
//		for (Role role : roles) {
//			assignedRoleMap.put(role.getId(), role);
//		}
//
//		List<Role> userRoles = new ArrayList<>();
//		List<Role> allRoles = roleService.findAll();
//		for (Role role : allRoles) {
//			if (assignedRoleMap.containsKey(role.getId())) {
//				userRoles.add(role);
//			} else {
//				userRoles.add(null);
//			}
//		}
//		return userRoles;
//	}
}
