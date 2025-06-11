package org.example.hello.mappers;

import ch.qos.logback.core.model.ComponentModel;
import org.apache.catalina.User;
import org.example.hello.DTO_storage.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);

}
