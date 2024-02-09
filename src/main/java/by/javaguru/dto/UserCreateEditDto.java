package by.javaguru.dto;

import by.javaguru.model.Role;
import by.javaguru.validator.IsAdult;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateEditDto {

    Long id;

    @NotBlank(message = "Username should be not null")
    String username;

    @NotBlank(message = "Password should be not null")
    String password;

    @IsAdult(message = "Birthdate should be over 18")
    LocalDate birthDate;

    @NotBlank(message = "Firstname should be not null")
    String firstname;

    @NotBlank(message = "Lastname should be not null")
    String lastname;

    Role role;
}
