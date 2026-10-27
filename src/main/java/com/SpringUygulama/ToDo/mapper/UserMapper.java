package com.SpringUygulama.ToDo.mapper;

import com.SpringUygulama.ToDo.dto.request.UserRequestDTO;
import com.SpringUygulama.ToDo.dto.response.UserResponseDTO;
import com.SpringUygulama.ToDo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User mapUserRequestDTOToUser(UserRequestDTO userRequestDTO) {
        return modelMapper.map(userRequestDTO,User.class);
    }

    public UserResponseDTO mapUserToUserDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }
}
