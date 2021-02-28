package ru.job4j.chat.repo;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Role;
import java.util.ArrayList;
import java.util.List;


@DataJpaTest
@RunWith(SpringRunner.class)
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void whenCreatePerson() {
        Person person = new Person();
        Role role = new Role("Super user");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        roleRepository.save(role);
        person.setRoles(roles);
        personRepository.save(person);
        Assert.assertEquals(person, personRepository.findById(person.getId()).orElse(new Person())
        );
    }
}