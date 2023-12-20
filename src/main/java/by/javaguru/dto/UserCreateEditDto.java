package by.javaguru.dto;

import by.javaguru.model.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserCreateEditDto {

    Long id;
    String username;
    String password;
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
}
