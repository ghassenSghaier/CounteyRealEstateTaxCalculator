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
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.tn.taxecommune.entity.Role;
import gov.tn.taxecommune.entity.User;
import gov.tn.taxecommune.repository.ContribuableRepository;
import gov.tn.taxecommune.repository.UserRepository;
import gov.tn.taxecommune.web.dto.UserDto;
import gov.tn.taxecommune.web.dto.UserUpdateDto;

/**
 * Created by Keno&Kemo on 18.10.2017..
 */

@Service
public class ContribuableService {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private UserRepository userRepository;
	
	private ContribuableRepository cRepository;

	private RoleService roleService;

	private CacheManager cacheManager;

	public ContribuableService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository,
			@Lazy RoleService roleService, CacheManager cacheManager) {

		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.cacheManager = cacheManager;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	// region find methods
	// ==============================================================================================
	@Cacheable(value = "cache.allContribuables")
	public List<User> findAll() {
		return cRepository.findAll();
	}

	@Cacheable(value = "cache.allUsersPageable")
	public Page<User> findAllPageable(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Cacheable(value = "cache.userByEmail", key = "#email", unless = "#result == null")
	public User findByEmail(String email) {
		return userRepository.findByEmailEagerly(email);
	}

	@Cacheable(value = "cache.userById", key = "#id", unless = "#result == null")
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public Page<User> findByIdPageable(Long id, Pageable pageRequest) {
		Optional<User> user = userRepository.findById(id);
		List<User> users = user.isPresent() ? Collections.singletonList(user.get()) : Collections.emptyList();
		return new PageImpl<>(users, pageRequest, users.size());
	}

	public User findByEmailAndIdNot(String email, Long id) {
		return userRepository.findByEmailAndIdNot(email, id);
	}

	public User findByUsernameAndIdNot(String username, Long id) {
		return userRepository.findByUsernameAndIdNot(username, id);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	// region Find eagerly
	public User findByIdEagerly(Long id) {
		return userRepository.findByIdEagerly(id);
	}

	@Cacheable(value = "cache.allUsersEagerly")
	public List<User> findAllEagerly() {
		return userRepository.findAllEagerly();
	}
	// endregion

	// region Find by containing
	@Cacheable(value = "cache.byNameContaining")
	public Page<User> findByNameContaining(String name, Pageable pageable) {
		return userRepository.findByNomContainingOrderByIdAsc(name, pageable);
	}

	@Cacheable(value = "cache.bySurnameContaining")
	public Page<User> findBySurnameContaining(String surname, Pageable pageable) {
		return userRepository.findByPrenomContainingOrderByIdAsc(surname, pageable);
	}

	@Cacheable(value = "cache.byUsernameContaining")
	public Page<User> findByUsernameContaining(String username, Pageable pageable) {
		return userRepository.findByUsernameContainingOrderByIdAsc(username, pageable);
	}

	@Cacheable(value = "cache.byEmailContaining")
	public Page<User> findByEmailContaining(String email, Pageable pageable) {
		return userRepository.findByEmailContainingOrderByIdAsc(email, pageable);
	}
	// endregion

	// ==============================================================================================
	// endregion

	@Transactional
	@CacheEvict(value = { "cache.allUsersPageable", "cache.allUsers", "cache.userByEmail", "cache.userById",
			"cache.allUsersEagerly", "cache.byNameContaining", "cache.bySurnameContaining",
			"cache.byUsernameContaining", "cache.byEmailContaining" }, allEntries = true)
	public void save(User user) {
		userRepository.save(user);
	}

	@CacheEvict(value = { "cache.allUsersPageable", "cache.allUsers", "cache.userByEmail", "cache.userById",
			"cache.allUsersEagerly", "cache.byNameContaining", "cache.bySurnameContaining",
			"cache.byUsernameContaining", "cache.byEmailContaining" }, allEntries = true)
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public User createNewAccount(UserDto userDto) {
		User user = new User();
		user.setNom(userDto.getNom());
		user.setPrenom(userDto.getPrenom());
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		user.setRoles(Collections.singletonList(roleService.findByNom("ROLE_USER")));
		return user;
	}

	public User getUpdatedUser(User persistedUser, UserUpdateDto userUpdateDto) {
		persistedUser.setNom(userUpdateDto.getNom());
		persistedUser.setPrenom(userUpdateDto.getPrenom());
		persistedUser.setUsername(userUpdateDto.getUsername());
		persistedUser.setEmail(userUpdateDto.getEmail());
		persistedUser.setRoles(getAssignedRolesList(userUpdateDto));
		persistedUser.setEnabled(userUpdateDto.isEnabled());
		return persistedUser;
	}

	public List<Role> getAssignedRolesList(UserUpdateDto userUpdateDto) {
		Map<Long, Role> assignedRoleMap = new HashMap<>();
		List<Role> roles = userUpdateDto.getRoles();
		for (Role role : roles) {
			assignedRoleMap.put(role.getId(), role);
		}

		List<Role> userRoles = new ArrayList<>();
		List<Role> allRoles = roleService.findAll();
		for (Role role : allRoles) {
			if (assignedRoleMap.containsKey(role.getId())) {
				userRoles.add(role);
			} else {
				userRoles.add(null);
			}
		}
		return userRoles;
	}
}
