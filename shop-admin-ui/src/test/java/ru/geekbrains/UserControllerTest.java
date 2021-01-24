package ru.geekbrains;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.persist.repo.UserRepository;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        userRepository.deleteAllInBatch();
    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testUserCreation() throws Exception {
//        mvc.perform(post("/user")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("id", "1l")
//                .param("name", "New user")
//                .param("email", "@mail")
//                .param("password", "123")
//                .param("firstName", "Petya")
//                .param("lastName", "Ivanov")
//                .param("roles", "Admin","user")
//                .with(csrf())
//        )
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/users"));
//
//
//        Optional<User> opt = userRepository.findOne(Example.of(new User(1l,"New user","123","Petya","Ivanov")));
//
//        assertTrue(opt.isPresent());
//        assertEquals("New user", opt.get().getName());
//        assertEquals("Petya", opt.get().getFirstName());
//        assertEquals("Ivanov", opt.get().getLastName());
    }
}