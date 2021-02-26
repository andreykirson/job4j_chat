package ru.job4j.chat.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.PersonService;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/25/2021
 */

@Service
@Slf4j
public class PersonDetailsServiceImpl implements UserDetailsService {

    private final PersonService personService;

    public PersonDetailsServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> person = personService.findPersonByEmail(email);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException("Person with email: " + email + "not found");
        }
        JWTPerson jwtPerson = JWTPersonFactory.create(person.get());
        log.info("In loadUserByUsername of PersonDetailsServiceImpl - person with email: {} successfully loaded", email);
        log.info("jwtPerson: {}", jwtPerson.toString());
        return jwtPerson;
    }
}