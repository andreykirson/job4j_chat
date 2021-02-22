//package ru.job4j.chat.controller;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//import ru.job4j.chat.model.Person;
//import ru.job4j.chat.model.Role;
//import ru.job4j.chat.repo.PersonRepository;
//import ru.job4j.chat.repo.RoleRepository;
//
///**
// * @author Andrey
// * @version 1
// * @date 2/19/2021
// */
//@RestController
//@RequestMapping("/register")
//public class RegControl {
//
//    private final PasswordEncoder encoder;
//    private final PersonRepository personRepository;
//    private final RoleRepository roleRepository;
//
//    public RegControl(PasswordEncoder encoder, PersonRepository personRepository, RoleRepository roleRepository) {
//        this.encoder = encoder;
//        this.personRepository = personRepository;
//        this.roleRepository = roleRepository;
//    }
//
//
//    @PostMapping("/register")
//    public String save(@RequestParam String name, @RequestParam String password, @RequestParam String email) {
//        Role role = roleRepository.findByRole("ROLE_USER");
//        Person person = new Person();
//        person.setEnabled(true);
//        person.setName(name);
//        person.setPassword(encoder.encode(password));
//        person.setEmail(email);
//        person.setRole(role);
//        System.out.println(name);
//        personRepository.save(person);
//        return "redirect:/login";
//    }
//
//    @GetMapping("/register")
//    public String reg() {
//        return "/register";
//    }
//
//
//}