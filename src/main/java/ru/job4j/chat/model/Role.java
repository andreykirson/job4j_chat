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
}
