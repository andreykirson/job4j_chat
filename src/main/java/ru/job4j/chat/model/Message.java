package ru.job4j.chat.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Andrey
 * @version 1
 * @date 2/16/2021
 */

@Data
@Table(name = "messages")
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    @Column(name = "createddate")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(
            name = "author_id"
    )
    private Person author;

    @ManyToOne
    @JoinColumn(
            name = "room_id"
    )
    private Room room;

    public Message(int i) {
    }

    public Message() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return id == message.id &&
                Objects.equals(content, message.content) &&
                Objects.equals(createdDate, message.createdDate) &&
                Objects.equals(author, message.author) &&
                Objects.equals(room, message.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, createdDate, author, room);
    }
}
