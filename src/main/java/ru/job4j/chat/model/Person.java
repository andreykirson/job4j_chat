package ru.job4j.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;


/**
 * @author Andrey
 * @version 1
 * @date 2/16/2021
 */

@Data
@Table(name = "persons")
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "person_roles", joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "participants",
            joinColumns =
                    @JoinColumn(name = "person_id"),
            inverseJoinColumns =
                    @JoinColumn(name = "room_id")
    )
    private List<Room> rooms;

    public Person() {

    }

    public Person(int id) {
    }

    public Person(int id, String name, String email, String password, boolean b) {
    }
}
