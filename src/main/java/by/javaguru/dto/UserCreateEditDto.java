package by.javaguru.dto;

import by.javaguru.model.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserCreateEditDto {

    Long id;
    String username;
    String password;
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
}
