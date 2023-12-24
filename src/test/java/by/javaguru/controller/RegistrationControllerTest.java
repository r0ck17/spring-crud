package by.javaguru.controller;

import by.javaguru.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(RegistrationController.class)
class RegistrationControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registration"))
                .andExpect(view().name("registration"));
    }

    @Test
    void userLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/registration"))
                .andExpect(model().attribute("userRegistered", "Successful registration"));

        verify(userService).save(any());
    }
}