package com.SpringUygulama.ToDo.business.service;

import com.SpringUygulama.ToDo.dto.request.LoginRequest;
import com.SpringUygulama.ToDo.dto.request.UserRequestDTO;
import com.SpringUygulama.ToDo.dto.response.TaskResponseDTO;
import com.SpringUygulama.ToDo.dto.response.TokenResponse;
import com.SpringUygulama.ToDo.dto.response.UserResponseDTO;
import com.SpringUygulama.ToDo.entity.User;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    TokenResponse saveUser(UserRequestDTO userRequestDTO);

    UserResponseDTO getUserById(Long userId);

    List<TaskResponseDTO> getAllTasksByUser(Long id);

    TokenResponse login(@Valid LoginRequest loginRequest);
}
