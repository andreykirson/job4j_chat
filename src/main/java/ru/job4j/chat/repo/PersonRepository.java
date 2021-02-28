package ru.job4j.chat.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Room;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/19/2021
 */

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findAllByRooms(Room room);
    List<Person> findAll();
    Optional<Person> findPersonById(int id);
    Optional<Person> findByEmail(String email);
    Optional<Person> findByEmailAndPassword(String email, String password);
}
