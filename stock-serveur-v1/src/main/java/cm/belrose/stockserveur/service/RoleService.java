package cm.belrose.stockserveur.service;

import cm.belrose.stockserveur.model.ERole;
import cm.belrose.stockserveur.model.Role;
import cm.belrose.stockserveur.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleService {
    Optional<Role> findByName(ERole name);

    void save(Role role);

    void saveAll(Set<Role> listOfRoles);

    List<Role> findAllRole();

}
