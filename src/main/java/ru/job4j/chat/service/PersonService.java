package ru.job4j.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repo.PersonRepository;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/20/2021
 */

@Service
public class PersonService implements IPersonService {

    @Autowired
    PersonRepository personRepository;

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
        return personRepository.findAll();
    }

    @Override
    public void saveOrUpdate(Person person) {
        personRepository.save(person);
    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

}
