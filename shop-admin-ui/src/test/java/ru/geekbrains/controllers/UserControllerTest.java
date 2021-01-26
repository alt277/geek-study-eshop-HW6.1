package ru.geekbrains.controllers;



import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.controller.repr.UserRepr;
import ru.geekbrains.persist.model.*;
import ru.geekbrains.persist.repo.RoleRepository;
import ru.geekbrains.persist.repo.UserRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void init() {
        userRepository.deleteAllInBatch();
    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testUserCreation() throws Exception {

                 User user= userRepository.save(new User(1L,"admin","1234","Alex","Ivanov"));
        mvc.perform(get("/user/" +user.getId()+"/edit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1l")
                .param("name", "admin")
                .param("password", "1234")
                .param("firstName", "Alex")
                .param("lastName", "Ivanov")
                .param("roles", "admin","user")
                .with(csrf())
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user_form"));


        Optional<User> opt = userRepository.findOne(Example.of(new User(1L,"admin","1234","Alex","Ivanov")));

        assertTrue(opt.isPresent());
        assertEquals("admin", opt.get().getName());
        assertEquals("Alex", opt.get().getFirstName());
        assertEquals("Ivanov", opt.get().getLastName());
    }

}
