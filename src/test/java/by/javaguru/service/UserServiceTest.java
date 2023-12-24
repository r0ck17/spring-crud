package by.javaguru.service;

import by.javaguru.dto.UserCreateEditDto;
import by.javaguru.dto.UserReadDto;
import by.javaguru.mapper.UserMapper;
import by.javaguru.model.Role;
import by.javaguru.model.User;
import by.javaguru.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @Test
    void findById() {
        User user = generateUser();
        UserReadDto expected = generateUserReadDto();

        doReturn(Optional.of(user)).when(userRepository).findById(1L);
        doReturn(expected).when(userMapper).toDto(user);

        Optional<UserReadDto> actual = userService.findById(1L);

        verify(userRepository).findById(1L);
        verify(userMapper).toDto(user);
        assertThat(actual).isNotEmpty();
        assertEquals(expected, actual.get());
    }

    @Test
    void save() {
        User user = generateUser();
        UserCreateEditDto userDto = generateUserCreateEditDtoWithoutId();
        UserReadDto expected = generateUserReadDto();

        doReturn(user).when(userMapper).toModel(userDto);
        doReturn(user).when(userRepository).save(user);
        doReturn(expected).when(userMapper).toDto(user);

        UserReadDto actual = userService.save(userDto);

        verify(userRepository).save(user);
        verify(userMapper).toModel(userDto);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        User user = generateUser();
        UserCreateEditDto userDto = generateUserCreateEditDtoWithoutId();
        UserReadDto expectedUser = generateUserReadDto();

        doReturn(user).when(userMapper).toModel(userDto);
        doReturn(user).when(userRepository).save(user);
        doReturn(expectedUser).when(userMapper).toDto(user);

        UserReadDto actualUser = userService.save(userDto);

        verify(userRepository).save(user);
        verify(userMapper).toModel(userDto);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void findAll() {
        User user = generateUser();
        UserReadDto userDto = generateUserReadDto();
        List<User> users = List.of(user);

        doReturn(users).when(userRepository).findAll();
        doReturn(userDto).when(userMapper).toDto(user);

        List<UserReadDto> actualUsers = userService.findAll();
        List<UserReadDto> ExpectedUsers = List.of(userDto);

        verify(userRepository).findAll();
        verify(userMapper).toDto(any());
        assertEquals(ExpectedUsers, actualUsers);
    }

    @Test
    void findUserByUsernameAndPassword() {
        User user = generateUser();
        UserReadDto userDto = generateUserReadDto();

        doReturn(Optional.of(user)).when(userRepository).findUserByUsernameAndPassword(anyString(), anyString());
        doReturn(userDto).when(userMapper).toDto(user);

        UserReadDto userById = userService.findUserByUsernameAndPassword("username", "password");

        verify(userRepository).findUserByUsernameAndPassword(anyString(), anyString());
        verify(userMapper).toDto(user);
        assertEquals(userDto, userById);
    }

    private static User generateUser() {
        return User.builder()
                .id(1L)
                .role(Role.USER)
                .birthDate(LocalDate.now())
                .username("username")
                .password("password")
                .firstname("firstname")
                .lastname("lastname")
                .build();
    }

    private static UserReadDto generateUserReadDto() {
        return UserReadDto.builder()
                .id(1L)
                .role(Role.USER)
                .birthDate(LocalDate.now())
                .username("username")
                .password("password")
                .firstname("firstname")
                .lastname("lastname")
                .build();
    }

    private static UserCreateEditDto generateUserCreateEditDtoWithoutId() {
        return UserCreateEditDto.builder()
                .role(Role.USER)
                .birthDate(LocalDate.now())
                .username("username")
                .password("password")
                .firstname("firstname")
                .lastname("lastname")
                .build();
    }
}