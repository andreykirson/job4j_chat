package ru.job4j.chat.service;

import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;

import java.util.List;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/20/2021
 */

public interface IPersonService {

    List<Person> findPersonInRoom(Room room);
    Optional<Person> findPersonById(int id);
    List<Person> findAll();
    void saveOrUpdate(Person person);
    void deletePerson(Person person);
    Optional<Person> findPersonByEmail(String email);
    Optional<Person> findPersonByEmailAndPassword(String email, String password);
    Person registerPerson(Person person);
}
