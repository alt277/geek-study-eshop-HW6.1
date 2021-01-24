package ru.geekbrains.controllers;

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
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.repo.CategoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void init() {
        categoryRepository.deleteAllInBatch();
    }

    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testCategoryCreation() throws Exception {
        Category category = categoryRepository.save(new Category("Jeans"));
        mvc.perform(get("/category/" +category.getId()+"/edit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "-1")
                .param("name", "Jeans")
                .with(csrf())
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attributeExists("category"))
                .andExpect(model().attributeExists("activePage"))
                .andExpect(model().attributeExists("edit"))
                .andExpect(view().name("category_form"));

        Optional<Category> opt = categoryRepository.findOne(Example.of(new Category("Jeans")));

        assertEquals("Jeans", opt.get().getName());
        assertTrue(opt.isPresent());
        assertEquals("Jeans", opt.get().getName());
    }
    @WithMockUser(value = "admin", password = "admin", roles = {"ADMIN"})
    @Test
    public void testCategoryDelete() throws Exception {
        Category category = categoryRepository.save(new Category("Phones"));
        mvc.perform(delete("/category/" +category.getId()+"/delete")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "-1")
                .param("name", "Phones")
                .with(csrf())
        )
                .andExpect(status().is3xxRedirection())
 //               .andExpect(model().attributeExists("activePage"))

                .andExpect(view().name("redirect:/categories"));

        Optional<Category> opt = categoryRepository.findOne(Example.of(new Category("Jeans")));
        assertTrue(opt.isEmpty());

    }
}
