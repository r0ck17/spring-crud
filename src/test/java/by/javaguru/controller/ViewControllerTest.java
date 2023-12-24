package by.javaguru.controller;

import by.javaguru.dto.UserReadDto;
import by.javaguru.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ViewController.class)
class ViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void userPage() throws Exception {
        UserReadDto dto = UserReadDto.builder()
                .id(1L)
                .firstname("ivan")
                .lastname("lastname")
                .birthDate(LocalDate.now())
                .username("username")
                .role(Role.USER)
                .password("password")
                .build();

        mockMvc.perform(get("/user").sessionAttr("user", dto))
                .andExpect(status().is2xxSuccessful());
    }
}