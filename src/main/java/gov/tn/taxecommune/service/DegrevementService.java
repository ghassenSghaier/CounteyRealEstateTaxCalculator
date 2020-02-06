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
import gov.tn.taxecommune.entity.Degrevement;
import gov.tn.taxecommune.repository.DegrevementRepository;

/**
 * Created by Keno&Kemo on 18.10.2017..
 */

@Service
public class DegrevementService {

	private DegrevementRepository degrevementRepository;
	private CacheManager cacheManager;

	public DegrevementService(DegrevementRepository degrevementRepository, CacheManager cacheManager) {
		super();
		this.degrevementRepository = degrevementRepository;
		this.cacheManager = cacheManager;
	}

	public DegrevementRepository getDegrevementRepository() {
		return degrevementRepository;
	}

	public void setDegrevementRepository(DegrevementRepository degrevementRepository) {
		this.degrevementRepository = degrevementRepository;
	}

	// region find methods
	// ==============================================================================================
	@Cacheable(value = "cache.allDegrevements")
	public List<Degrevement> findAll() {
		return degrevementRepository.findAll();
	}

	@Cacheable(value = "cache.allDegrevementsPageable")
	public Page<Degrevement> findAllPageable(Pageable pageable) {
		return degrevementRepository.findAll(pageable);
	}

	@Cacheable(value = "cache.degrevementById", key = "#id", unless = "#result == null")
	public Optional<Degrevement> findById(Long id) {
		return degrevementRepository.findById(id);
	}

	public Degrevement findByCodeDegrevement(String codeDegrevement) {
		return degrevementRepository.findByCodeDegrevement(codeDegrevement);
	}

	public Page<Degrevement> findByIdPageable(Long id, Pageable pageRequest) {
		Optional<Degrevement> degrev = degrevementRepository.findById(id);
		List<Degrevement> degrevements = degrev.isPresent() ? Collections.singletonList(degrev.get())
				: Collections.emptyList();
		return new PageImpl<>(degrevements, pageRequest, degrevements.size());
	}

	// region Find eagerly
//	public Degrevement findByIdEagerly(Long id) {
//		return degrevementRepository.findByIdEagerly(id);
//	}

	@Cacheable(value = "cache.allDegrevementsEagerly")
	public List<Degrevement> findAllEagerly() {
		return degrevementRepository.findAllEagerly();
	}
	// endregion

	// region Find by containing
//	@Cacheable(value = "cache.byTypeDegrevContaining")
//	public Page<Degrevement> findByTypeDegrevContaining(String type, Pageable pageable) {
//		return degrevementRepository.findByTypeDegrevementContainingOrderByIdAsc(type, pageable);
//	}
//
//	@Cacheable(value = "cache.byStatutDegrevContaining")
//	public Page<Degrevement> findByStatutDegrevContaining(String surname, Pageable pageable) {
//		return degrevementRepository.findByStatutDegrevementContainingOrderByIdAsc(surname, pageable);
//	}

//	@Cacheable(value = "cache.byDecisionDegrevContaining")
//	public Page<Degrevement> findByDecisionDegrevContaining(String username, Pageable pageable) {
//		return degrevementRepository.findByDecisionDegrevementContainingOrderByIdAsc(username, pageable);
//	}
//
//	@Cacheable(value = "cache.byMontantDegrevContaining")
//	public Page<Degrevement> findBymontantDegrevContaining(double email, Pageable pageable) {
//		return degrevementRepository.findByMontantDegrevementContainingOrderByIdAsc(email, pageable);
//	}
	// endregion

	// ==============================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allDegrevements", "cache.allDegrevementsPageable", "cache.degrevementById",
			"cache.allDegrevementsEagerly", "cache.byTypeDegrevContaining", "cache.byStatutDegrevContaining",
			"cache.byDecisionDegrevContaining", "cache.byMontantDegrevContaining" }, allEntries = true)
	public void save(Degrevement user) {
		degrevementRepository.save(user);
	}

	@CacheEvict(value = { "cache.allDegrevements", "cache.allDegrevementsPageable", "cache.degrevementById",
			"cache.allDegrevementsEagerly", "cache.byTypeDegrevContaining", "cache.byStatutDegrevContaining",
			"cache.byDecisionDegrevContaining", "cache.byMontantDegrevContaining" }, allEntries = true)
	public void deleteById(Long id) {
		degrevementRepository.deleteById(id);
	}

//	public User createNewAccount(UserDto userDto) {
//		Degrevement degrev = new Degrevement();
//		user.setNom(userDto.getNom());
//		user.setPrenom(userDto.getPrenom());
//		user.setUsername(userDto.getUsername());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
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
