package gov.tn.taxecommune.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import gov.tn.taxecommune.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmailAndIdNot(String email, Long id);

    User findByUsernameAndIdNot(String username, Long id);

    Page<User> findByUsernameContainingOrderByIdAsc(String username, Pageable pageable);

    Page<User> findByEmailContainingOrderByIdAsc(String email, Pageable pageable);

    Page<User> findByNomContainingOrderByIdAsc(String name, Pageable pageable);

    Page<User> findByPrenomContainingOrderByIdAsc(String surname, Pageable pageable);

    //region Find eagerly
    //==========================================================================
    @Query("SELECT u FROM User u JOIN FETCH u.roles")
    List<User> findAllEagerly();

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = (:email)")
    User findByEmailEagerly(@Param("email") String email);

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = (:id)")
    User findByIdEagerly(@Param("id") Long id);

    //==========================================================================
    //endregion
    Boolean existsByUsername(String username);

    @Query("SELECT c FROM User c where c.username = ?1 AND c.password = ?2")
    User findByLoginAndPassword(String username, String password);
}
