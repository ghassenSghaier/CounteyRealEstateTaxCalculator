package gov.tn.taxecommune.service;

import java.util.List;
import java.util.Optional;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import gov.tn.taxecommune.entity.CategorieArticle;
import gov.tn.taxecommune.repository.CategorieArticleRepository;

@Service
public class CategorieArticleService {

	private CategorieArticleRepository categorieArticleRepository;
	private CacheManager cacheManager;

	public CategorieArticleService(CategorieArticleRepository categorieArticleRepository, CacheManager cacheManager) {

		this.categorieArticleRepository = categorieArticleRepository;
		this.cacheManager = cacheManager;
	}

	public CategorieArticleRepository getCategorieArticleRepository() {
		return categorieArticleRepository;
	}

	public void setCategorieArticleRepository(CategorieArticleRepository categorieArticleRepository) {
		this.categorieArticleRepository = categorieArticleRepository;
	}

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	// region find methods
	// ==============================================================================================
	@Cacheable(value = "cache.allArticlesCategories")
	public List<CategorieArticle> findAll() {
		return categorieArticleRepository.findAll();
	}

	@Cacheable(value = "cache.allArticlesCategoriesPageable")
	public Page<CategorieArticle> findAllPageable(Pageable pageable) {
		return categorieArticleRepository.findAll(pageable);
	}

//	@Cacheable(value = "cache.categorieByCodeCategorie", key = "#email", unless = "#result == null")
//	public List<CategorieArticle> findByNomCategorie(String email) {
//		return categorieArticleRepository.findByCodeCategorie(email);
//	}

//	@Cacheable(value = "cache.categorieByCodeCategorie", key = "#email", unless = "#result == null")
//	public Optional<Categorie> findByCodeCategorie(String email) {
//		return categorieVocationRepository.findByCodeCategorie(email);
//	}

//	@Cacheable(value = "cache.categorieByIntervalSizeCategorie", key = "#size", unless = "#result == null")
//	public List<Categorie> findByIntervalSizeCategorie(double size) {
//		return categorieVocationRepository.findByIntervalCouvert(size);
//	}

	@Cacheable(value = "cache.categorieArticleById", key = "#id", unless = "#result == null")
	public Optional<CategorieArticle> findById(Long id) {
		return categorieArticleRepository.findById(id);
	}

//	@Cacheable(value = "cache.categoriesByNomVocation", key = "#nomVocation", unless = "#result == null")
//	public List<Categorie> findByByNomVocation(String nomVocation) {
//		return categorieArticleRepository.findByNomVocationEagerly(nomVocation);
//	}

//	public Page<Categorie> findByIdPageable(Long id, Pageable pageRequest) {
//		Optional<Categorie> categorie = categorieVocationRepository.findById(id);
//		List<Categorie> categories = categorie.isPresent() ? Collections.singletonList(categorie.get())
//				: Collections.emptyList();
//		return new PageImpl<>(categories, pageRequest, categories.size());
//	}
//
//	public Categorie findByNomCategorieAndIdNot(String email, Long id) {
//		return categorieVocationRepository.findByNomCategorieAndIdNot(email, id);
//	}
//
//	public Categorie findByCodeCategorieAndIdNot(String username, Long id) {
//		return categorieVocationRepository.findByCodeCategorieAndIdNot(username, id);
//	}
//
//	public Optional<Categorie> findByCodeVocation(String username) {
//		return categorieVocationRepository.findByCodeCategorie(username);
//	}
//
//	// region Find eagerly
////	public Categorie findByIdEagerly(Long id) {
////		return categorieVocationRepository.findByIdEagerly(id);
////	}
//
////	@Cacheable(value = "cache.allCategoriesEagerly")
////	public List<Categorie> findAllEagerly() {
////		return categorieVocationRepository.findAllEagerly();
////	}
//	// endregion
//
//	// region Find by containing
//	@Cacheable(value = "cache.byNomCategorieContaining")
//	public Page<Categorie> findByNameContaining(String name, Pageable pageable) {
//		return categorieVocationRepository.findByNomCategorieContainingOrderByIdAsc(name, pageable);
//	}
//
//	@Cacheable(value = "cache.byCodeCategorieContaining")
//	public Page<Categorie> findByCodeCategorieContaining(String surname, Pageable pageable) {
//		return categorieVocationRepository.findByCodeCategorieContainingOrderByIdAsc(surname, pageable);
//	}
//
//	// ==============================================================================================
//	// endregion
//
	@Transactional
	@CacheEvict(value = { "cache.allArticlesCategories", "cache.allArticlesCategoriesPageable",
			"cache.categorieArticleById" }, allEntries = true)
	public void save(CategorieArticle user) {
		categorieArticleRepository.save(user);
	}

	@CacheEvict(value = { "cache.allArticlesCategories", "cache.allArticlesCategoriesPageable",
			"cache.categorieArticleById" }, allEntries = true)
	public void deleteById(Long id) {
		categorieArticleRepository.deleteById(id);
	}

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
