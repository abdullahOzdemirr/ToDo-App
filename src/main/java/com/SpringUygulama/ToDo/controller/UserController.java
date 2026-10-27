package com.SpringUygulama.ToDo.controller;

import com.SpringUygulama.ToDo.business.service.MailService;
import com.SpringUygulama.ToDo.business.service.UserService;
import com.SpringUygulama.ToDo.dto.request.UserRequestDTO;
import com.SpringUygulama.ToDo.dto.response.TaskResponseDTO;
import com.SpringUygulama.ToDo.dto.response.TokenResponse;
import com.SpringUygulama.ToDo.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam Long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasksByUser(@RequestParam Long id){
        return new ResponseEntity<>(userService.getAllTasksByUser(id),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<TokenResponse> saveUser(@RequestBody @Valid UserRequestDTO userRequestDTO){
        return new ResponseEntity<>(userService.saveUser(userRequestDTO),HttpStatus.OK);
    }
}
