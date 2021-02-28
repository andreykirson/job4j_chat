package ru.job4j.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));
            Optional<Person> user = personService.findPersonByEmail(email);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException("User with username: " + email + " not found");
            }
            String token = jwtTokenProvider.createToken(email, user.get().getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", email);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
