package by.javaguru.service;

import by.javaguru.dto.UserCreateEditDto;
import by.javaguru.dto.UserReadDto;
import by.javaguru.exception.UserNotFoundException;
import by.javaguru.mapper.UserMapper;
import by.javaguru.model.User;
import by.javaguru.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<UserReadDto> findById(Long id) {
        Optional<User> userById = userRepository.findById(id);
        return userById.map(userMapper::toDto);
    }

    public UserReadDto save(UserCreateEditDto userDto) {
        User savedUser = userRepository.save(userMapper.toModel(userDto));
        return userMapper.toDto(savedUser);
    }

    public UserReadDto update(UserCreateEditDto userDto) {
        User savedUser = userRepository.save(userMapper.toModel(userDto));
        return userMapper.toDto(savedUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserReadDto findUserByUsernameAndPassword(String username, String password) {
        Optional<User> user =
                userRepository.findUserByUsernameAndPassword(username, password);

        return user.map((u) -> userMapper.toDto(user.get()))
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
