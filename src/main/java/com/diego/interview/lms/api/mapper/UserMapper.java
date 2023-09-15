package com.diego.interview.lms.api.mapper;

import com.diego.interview.lms.api.dto.CreateUserDTO;
import com.diego.interview.lms.api.dto.UserDTO;
import com.diego.interview.lms.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(CreateUserDTO createUserDTO);
    CreateUserDTO toDTO(User user);

    UserDTO toUserDTO(User user);
}
