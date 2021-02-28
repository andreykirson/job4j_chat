package ru.job4j.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Andrey
 * @version 1
 * @date 2/16/2021
 */

@Data
@Table(name = "rooms")
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(name = "createddate")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Person creator;

    @ManyToMany(mappedBy = "rooms", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Person> participants;

    public Room(int id, String new_room, Date date) {
    }

    public Room(int id) {
    }

    public Room() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return id == room.id &&
                Objects.equals(title, room.title) &&
                Objects.equals(createdDate, room.createdDate) &&
                Objects.equals(creator, room.creator) &&
                Objects.equals(participants, room.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, createdDate, creator, participants);
    }
}
