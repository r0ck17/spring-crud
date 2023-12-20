package by.javaguru.mapper;

import by.javaguru.dto.UserCreateEditDto;
import by.javaguru.dto.UserReadDto;
import by.javaguru.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserReadDto toDto(User user);
    User toModel(UserCreateEditDto dto);
}
