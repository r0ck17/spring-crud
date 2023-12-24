package by.javaguru.controller;

import by.javaguru.dto.UserReadDto;
import by.javaguru.model.Role;
import by.javaguru.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    private static final String BASE_URL = "/users";

    @Test
    void allUsersPage() throws Exception {
        UserReadDto user1 = getUser(1L, Role.USER);
        UserReadDto user2 = getUser(2L, Role.ADMIN);
        List<UserReadDto> users = List.of(user1, user2);
        doReturn(users).when(userService).findAll();

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("users"))
                .andExpect(model().attribute("users", users));

        verify(userService).findAll();
    }

    @Test
    void updateUserPage() throws Exception {
        UserReadDto user = getUser();
        doReturn(Optional.of(user)).when(userService).findById(anyLong());

        mockMvc.perform(get(BASE_URL + "/{id}", 1L))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("updateUser"))
                .andExpect(model().attribute("user", user));

        verify(userService).findById(anyLong());
    }

    @Test
    void updateUser() throws Exception {
        mockMvc.perform(put(BASE_URL))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
        verify(userService).save(any());
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/{id}", 1L))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
        verify(userService).delete(any());
    }

    private static UserReadDto getUser() {
        return UserReadDto.builder()
                .id(1L)
                .username("user1")
                .password("pass1")
                .role(Role.USER)
                .build();
    }

    private static UserReadDto getUser(Long id, Role role) {
        return UserReadDto.builder()
                .id(id)
                .username("user1")
                .password("pass1")
                .role(role)
                .build();
    }
}