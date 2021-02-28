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
@Table(name ="roles")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<Person> persons;

    public Role(int i, String user) {
    }

    public Role() {

    }

    public Role(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role1 = (Role) o;
        return id == role1.id &&
                Objects.equals(role, role1.role) &&
                Objects.equals(persons, role1.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, persons);
    }
}
