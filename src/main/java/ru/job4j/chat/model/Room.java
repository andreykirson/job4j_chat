package ru.job4j.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @ManyToMany(mappedBy = "rooms")
    @JsonIgnore
    private List<Person> participants;

}
