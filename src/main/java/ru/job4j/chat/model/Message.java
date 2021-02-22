package ru.job4j.chat.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

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

}
