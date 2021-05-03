package cm.belrose.stockserveur.repository;

import cm.belrose.stockserveur.model.ERole;
import cm.belrose.stockserveur.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *@author  Ngnawen Samuel
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
