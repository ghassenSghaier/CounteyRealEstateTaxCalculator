package gov.tn.taxecommune.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import gov.tn.taxecommune.entity.Role;
import gov.tn.taxecommune.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Keno&Kemo on 04.11.2017..
 */
@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //region Find methods
    //==================================================================================
    @Cacheable("cache.allRoles")
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Cacheable(value = "cache.roleByNom", key = "#name", unless = "#result == null")
    public Role findByNom(String name) {
        return roleRepository.findByNom(name).get();
    }

    @Cacheable(value = "cache.roleById", key = "#id", unless = "#result == null")
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
    //==================================================================================
    //endregion

    @CacheEvict(value = {"cache.allRoles" , "cache.roleByNom", "cache.roleById"}, allEntries = true)
    public void save(Role role) {
        roleRepository.save(role);
    }

    public boolean checkIfRoleNameIsTaken(List<Role> allRoles, Role role, Role persistedRole) {
        boolean roleNameAlreadyExists = false;
        for (Role role1 : allRoles) {
            //Check if the role name is edited and if it is taken
            if (!role.getNom().equals(persistedRole.getNom())
                    && role.getNom().equals(role1.getNom())) {
                roleNameAlreadyExists = true;
            }
        }
        return roleNameAlreadyExists;
    }


}
