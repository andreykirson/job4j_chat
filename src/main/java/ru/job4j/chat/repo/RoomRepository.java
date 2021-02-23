package ru.job4j.chat.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
public interface RoomRepository extends CrudRepository<Room, Integer> {

    Room save(Room room);

    List<Room> findAll();

    List<Room> findAllByTitle(String title);

    Optional<Room> findRoomById(int id);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(
            nativeQuery = true,
            value = "delete from participants p where p.room_id = :room"
    )
    void removeAllParticipantsInRoom(Room room);

    void delete(Room room);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(
            nativeQuery = true,
            value = "insert into participants (room_id, person_id) values(:room, :person)"
    )
    int addParticipant(Person person, Room room);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(
            nativeQuery = true,
            value = "delete from participants p where p.room_id = :room and p.person_id = :person"
    )
    void removeParticipantsByPerson(Room room, Person person);



    @Query( nativeQuery = true,
            value = "select * from persons p where id = (select person_id from participants where person_id = :person and room_id = :room)"
    )
    int findByParticipant(Room room, Person person);

}