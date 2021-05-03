package cm.belrose.stockserveur.service.impl;

import cm.belrose.stockserveur.model.ERole;
import cm.belrose.stockserveur.model.Role;
import cm.belrose.stockserveur.repository.RoleRepository;
import cm.belrose.stockserveur.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Le 08/11/2020
 * @author Ngnawen Samuel
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void save(Role role) {
        Assert.notNull(role,"Role must not be null");
        roleRepository.save(role);
    }

    @Override
    public void saveAll(Set<Role> listOfRoles) {
        roleRepository.saveAll(listOfRoles);
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }
}
