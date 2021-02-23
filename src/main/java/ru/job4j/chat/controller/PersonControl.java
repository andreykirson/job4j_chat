package ru.job4j.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.exception.ResourceNotFoundException;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.PersonService;
import java.util.List;

/**
 * @author Andrey
 * @version 1
 * @date 2/21/2021
 */

@RestController
@RequestMapping("/person")
public class PersonControl {

    @Autowired
    PersonService personService;

    @DeleteMapping
    public void deletePerson(@RequestParam int id) {
        if (personService.findPersonById(id).isEmpty()) {
            throw new ResourceNotFoundException();
        }
        personService.deletePerson(new Person(id));
    }

    @GetMapping("/{id}")
    public Person getById( @PathVariable int id) {
        return personService.findPersonById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/all")
    public List<Person> getAll() {
        return personService.findAll();
    }

}
