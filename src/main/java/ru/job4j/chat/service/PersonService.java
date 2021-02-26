package ru.job4j.chat.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.chat.exception.ResourceNotFoundException;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repo.PersonRepository;
import ru.job4j.chat.repo.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/20/2021
 */

@Slf4j
@Service
public class PersonService implements IPersonService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public PersonService(BCryptPasswordEncoder passwordEncoder, PersonRepository personRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Person> findPersonInRoom(Room room) {
        return personRepository.findAllByRooms(room);
    }

    @Override
    public Optional<Person> findPersonById(int id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll() {
        List<Person> result = personRepository.findAll();
        log.info("IN register findAll : {} persons were found", result.size());
        return result;
    }

    @Override
    public void saveOrUpdate(Person person) {

    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    @Override
    public Optional<Person> findPersonByEmail(String email) {
        return personRepository.findByEmail(email);
    }

    @Override
    public Optional<Person> findPersonByEmailAndPassword(String email, String password) {
        return personRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Person registerPerson(Person person) {
        Optional<Role> role = roleRepository.findByRole("ROLE_USER");
        if (role.isEmpty()) {
            return null;
        }
        List<Role> roles = new ArrayList<>();
        roles.add(role.orElseThrow(ResourceNotFoundException::new));
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRoles(roles);
        Person registeredPerson = personRepository.save(person);
        log.info("IN register person : {} successfully registered", registeredPerson);
        return registeredPerson;
    }

}
