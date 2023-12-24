package by.javaguru.controller;

import by.javaguru.dto.UserReadDto;
import by.javaguru.exception.UserNotFoundException;
import by.javaguru.model.Role;
import by.javaguru.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("login"));
    }

    @Test
    void userLogin_roleUser() throws Exception {
        UserReadDto user = UserReadDto.builder()
                .role(Role.USER)
                .build();

        doReturn(user).when(userService)
                .findUserByUsernameAndPassword(anyString(), anyString());

        mockMvc.perform(post("/login")
                        .param("username", "username")
                        .param("password", "password")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));

        verify(userService).findUserByUsernameAndPassword(anyString(), anyString());
    }

    @Test
    void userLogin_roleAdmin() throws Exception {
        UserReadDto user = UserReadDto.builder()
                .role(Role.ADMIN)
                .build();

        doReturn(user).when(userService)
                .findUserByUsernameAndPassword(anyString(), anyString());

        mockMvc.perform(post("/login")
                        .param("username", "username")
                        .param("password", "password")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        verify(userService).findUserByUsernameAndPassword(anyString(), anyString());
    }

    @Test
    void userLogin_userNotFound() throws Exception {
        doThrow(UserNotFoundException.class)
                .when(userService)
                .findUserByUsernameAndPassword(anyString(), anyString());

        mockMvc.perform(post("/login")
                        .param("username", "username")
                        .param("password", "password")
                )
                .andExpect(view().name("login"))
                .andExpect(model().attribute("loginError", "Wrong username or password"));

        verify(userService).findUserByUsernameAndPassword(anyString(), anyString());
    }
}