package ru.job4j.chat.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.chat.model.Role;

import java.util.List;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/19/2021
 */

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByRole(String role);
    Optional<Role> findRoleById(int id);
    List<Role> findAll();
    Role save(Role role);
    int deleteByRole(String role);
}
