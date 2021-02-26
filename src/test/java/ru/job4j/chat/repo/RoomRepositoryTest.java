package ru.job4j.chat.repo;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.model.Room;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@RunWith(SpringRunner.class)
class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findRoomById() {
    }

    @Test
    public void whenCreate() {
        Room room = new Room();
        room.setTitle("new room");
        Person person = new Person();
        Role role = new Role("Super user");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        roleRepository.save(role);
        person.setRoles(roles);
        personRepository.save(person);
        room.setCreator(person);
        roomRepository.save(room);
        Assert.assertEquals(room, roomRepository.findById(room.getId()).orElse(new Room())
        );
    }
}