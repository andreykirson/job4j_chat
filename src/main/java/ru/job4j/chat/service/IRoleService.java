package ru.job4j.chat.service;

import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.model.Room;

import java.util.List;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/23/2021
 */

public interface IRoleService {
    Optional<Role> findRoleByRole(String role);
    Optional<Role> findRoleById(int id);
    List<Role> findAll();
    Role saveOrUpdate(Role role);
    int deleteByRole(String role);
}
