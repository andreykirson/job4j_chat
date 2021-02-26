package ru.job4j.chat.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey
 * @version 1
 * @date 2/25/2021
 */

public class JWTPersonFactory {

    public JWTPersonFactory() {

    }

    public static JWTPerson create(Person person) {
        return new JWTPerson (
                person.getId(),
                person.getName(),
                person.getEmail(),
                person.getPassword(),
                person.isEnabled(),
                mapToGrantedAuthorities(new ArrayList<>(person.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getRole())
                ).collect(Collectors.toList());
    }

}
