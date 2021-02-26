package ru.job4j.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.chat.auth.JWTPerson;
import ru.job4j.chat.auth.JWTPersonFactory;
import ru.job4j.chat.auth.JwtTokenProvider;
import ru.job4j.chat.auth.PersonDetailsServiceImpl;
import ru.job4j.chat.dto.AuthenticationRequestDto;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.PersonService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Andrey
 * @version 1
 * @date 2/25/2021
 */
@Slf4j
@RestController
@RequestMapping(value = "/chat/")
public class AuthenticationControl {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PersonService personService;
    private final PersonDetailsServiceImpl personDetailsService;

    @Autowired
    public AuthenticationControl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, PersonService personService, PersonDetailsServiceImpl personDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.personService = personService;
        this.personDetailsService = personDetailsService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        log.info("Start login rest controller");
        Map<Object, Object> response = new HashMap<>();
        try {
            String email = requestDto.getEmail();
            String password = requestDto.getPassword();
            Optional<Person> person = personService.findPersonByEmail(email);
            JWTPerson jwtPerson = JWTPersonFactory.create(person.get());
            log.info("The Authorities of jwtPerson is {} ", jwtPerson.getAuthorities());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtPerson.getUsername(), jwtPerson.getPassword(), jwtPerson.getAuthorities()));
             person = personService.findPersonByEmail(email);
            if (person.isEmpty()) {
                throw new UsernameNotFoundException("Person with email: " + email + " not found");
            } else {
                String token = jwtTokenProvider.createToken(email, person.get().getRoles());
                response.put("email", email);
                response.put("token", token);
            }
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
