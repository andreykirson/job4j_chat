package ru.job4j.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;


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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id &&
                enabled == person.enabled &&
                Objects.equals(name, person.name) &&
                Objects.equals(email, person.email) &&
                Objects.equals(password, person.password) &&
                Objects.equals(roles, person.roles) &&
                Objects.equals(rooms, person.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, enabled, roles, rooms);
    }
}
