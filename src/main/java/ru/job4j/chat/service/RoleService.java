package ru.job4j.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.repo.RoleRepository;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/23/2021
 */

@Service
public class RoleService implements IRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findRoleByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public Optional<Role> findRoleById(int id) {
        return roleRepository.findRoleById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role saveOrUpdate(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public int deleteByRole(String role) {
        return deleteByRole(role);
    }
}
