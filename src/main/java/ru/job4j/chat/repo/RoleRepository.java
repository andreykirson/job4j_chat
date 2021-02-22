package ru.job4j.chat.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.chat.model.Role;

/**
 * @author Andrey
 * @version 1
 * @date 2/19/2021
 */

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);
}
