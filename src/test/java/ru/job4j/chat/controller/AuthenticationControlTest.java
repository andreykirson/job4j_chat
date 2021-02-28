package ru.job4j.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.job4j.chat.auth.JWTPerson;
import ru.job4j.chat.auth.JWTPersonFactory;
import ru.job4j.chat.auth.JwtTokenProvider;
import ru.job4j.chat.auth.PersonDetailsServiceImpl;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.repo.PersonRepository;
import ru.job4j.chat.service.PersonService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class AuthenticationControlTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private PersonDetailsServiceImpl personDetailsService;

    private JWTPersonFactory jwtPersonFactory;

    private final ObjectMapper jsonConverter = new ObjectMapper();


    @Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test")).andExpect(status().isForbidden());
    }


    @Test
    public void shouldGenerateAuthToken() {

        Person p = new Person();
        p.setId(1);
        p.setName("Ivan");
        p.setEmail("yandex@ya.ru");
        p.setPassword("root");
        p.setRoles(emptyList());
        JWTPerson jwtPerson = JWTPersonFactory.create(p);

        Mockito.when(personDetailsService.loadUserByUsername("yandex@ya.ru")).thenReturn(jwtPerson);
        log.info("The person is {} :", personDetailsService.loadUserByUsername("yandex@ya.ru"));

    }


}
