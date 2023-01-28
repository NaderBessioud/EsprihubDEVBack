package tn.esprithub.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprithub.Entities.User;



public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
	@Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
	  @Query(
	            value = "SELECT * FROM user u  WHERE u.created_at BETWEEN ?1 AND ?2",
	            nativeQuery = true)
	    List<User> findAllActiveUsersNative(LocalDateTime start, LocalDateTime end);
	    @Query(
	            value = "SELECT * FROM user u  WHERE u.role=?1",
	            nativeQuery = true)
	    List<User> findUserRole(String role);

}
