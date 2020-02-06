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

import gov.tn.taxecommune.entity.Categorie;
import gov.tn.taxecommune.entity.CategorieTarificationprestation;
import gov.tn.taxecommune.entity.Tarificationprestation;
import gov.tn.taxecommune.repository.CategorieTarificationPrestationRepository;

/**
 * Created by Keno&Kemo on 18.10.2017..
 */

@Service
public class CategorieTarificationPrestationService {

	private CategorieTarificationPrestationRepository ctpRepository;
	private CacheManager cacheManager;

	public CategorieTarificationPrestationService(CategorieTarificationPrestationRepository ctpRepository,
			CacheManager cacheManager) {
		this.ctpRepository = ctpRepository;
		this.cacheManager = cacheManager;
	}

	public CategorieTarificationPrestationRepository getCtpRepository() {
		return ctpRepository;
	}

	public void setCtpRepository(CategorieTarificationPrestationRepository ctpRepository) {
		this.ctpRepository = ctpRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region find methods
	// ==============================================================================================
	@Cacheable(value = "cache.allCtps")
	public List<CategorieTarificationprestation> findAll() {
		return ctpRepository.findAll();
	}

	@Cacheable(value = "cache.ctpByCategorie")
	public Optional<List<CategorieTarificationprestation>> findByCVocation(Categorie cv) {
		return ctpRepository.findByACategorie(cv);
	}

	@Cacheable(value = "cache.ctpByTprestations")
	public Optional<List<CategorieTarificationprestation>> findByTPrestation(Tarificationprestation tp) {
		return ctpRepository.findByTPrestation(tp);
	}

	@Cacheable(value = "cache.allCtpsPageable")
	public Page<CategorieTarificationprestation> findAllPageable(Pageable pageable) {
		return ctpRepository.findAll(pageable);
	}

	@Cacheable(value = "cache.ctpByCode", key = "#code", unless = "#result == null")
	public Optional<CategorieTarificationprestation> findByCode(String code) {
		return ctpRepository.findByCodeCTPrestation(code);
	}

//	@Cacheable(value = "cache.ctpByCodeCategorie", key = "#codeCategorie", unless = "#result == null")
//	public Optional<List<CategorieTarificationprestation>> findByCodeCategorie(String codeCategorie) {
//		return ctpRepository.findByCodeCategorie(codeCategorie);
//	}

	@Cacheable(value = "cache.ctpById", key = "#id", unless = "#result == null")
	public Optional<CategorieTarificationprestation> findById(Long id) {
		return ctpRepository.findById(id);
	}

//	@Cacheable(value = "cache.ctpByCodeTarification", key = "#codeTarification", unless = "#result == null")
//	public Optional<List<CategorieTarificationprestation>> findByCodeTarification(String codeTarification) {
//		return ctpRepository.findByCodeTarification(codeTarification);
//	}

	public Page<CategorieTarificationprestation> findByIdPageable(Long id, Pageable pageRequest) {
		Optional<CategorieTarificationprestation> ctp = ctpRepository.findById(id);
		List<CategorieTarificationprestation> ctps = ctp.isPresent() ? Collections.singletonList(ctp.get())
				: Collections.emptyList();
		return new PageImpl<>(ctps, pageRequest, ctps.size());
	}

//	public CategorieTarificationprestation findByCodeAndIdNot(String email, Long id) {
//		return ctpRepository.findByCodeCTPrestationAndIdNot(email, id);
//	}
//
//	public CategorieTarificationprestation findByNomctPrestationAndIdNot(String username, Long id) {
//		return ctpRepository.findByCodeCTPrestationAndIdNot(username, id);
//	}

	public CategorieTarificationprestation findByNomctPrestation(String username) {
		return ctpRepository.findByNomCTPrestation(username);
	}

	public CategorieTarificationprestation findByCategorieTarificationPrestation(String codeCategorie,
			String codeTarification) {
		return ctpRepository.findByCodeTarificationAndCodeCategorie(codeCategorie, codeTarification);
	}

	// region Find eagerly
//	public User findByIdEagerly(Long id) {
//		return ctpRepository.findByIdEagerly(id);
//	}
//
//	@Cacheable(value = "cache.allUsersEagerly")
//	public List<User> findAllEagerly() {
//		return userRepository.findAllEagerly();
//	}
	// endregion

	// region Find by containing
//	@Cacheable(value = "cache.byNomContaining")
//	public Page<CategorieTarificationprestation> findByNameContaining(String name, Pageable pageable) {
//		return ctpRepository.findByNomCTPrestationContainingOrderByIdAsc(name, pageable);
//	}
//
//	@Cacheable(value = "cache.byCodeContaining")
//	public Page<CategorieTarificationprestation> findByCodeContaining(String email, Pageable pageable) {
//		return ctpRepository.findByCodeCTPrestationContainingOrderByIdAsc(email, pageable);
//	}
	// endregion

	// ==============================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allCtps", "cache.allCtpsPageable", "cache.ctpByCode", "cache.ctpById",
			"cache.byNomContaining", "cache.byCodeContaining", "cache.ctpByCodeCategorie",
			"cache.ctpByCodeTarification" }, allEntries = true)
	public void save(CategorieTarificationprestation ctp) {
		ctpRepository.save(ctp);
	}

	@CacheEvict(value = { "cache.allCtps", "cache.allCtpsPageable", "cache.ctpByCode", "cache.ctpById",
			"cache.byNomContaining", "cache.byCodeContaining", "cache.ctpByCodeCategorie",
			"cache.ctpByCodeTarification" }, allEntries = true)
	public void deleteById(Long id) {
		ctpRepository.deleteById(id);
	}
//
//	public User createNewAccount(UserDto userDto) {
//		User user = new User();
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
