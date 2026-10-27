package com.SpringUygulama.ToDo.business.impl;

import com.SpringUygulama.ToDo.business.service.MailService;
import com.SpringUygulama.ToDo.business.service.UserService;
//import com.SpringUygulama.ToDo.business.util.Role;
import com.SpringUygulama.ToDo.dto.request.LoginRequest;
import com.SpringUygulama.ToDo.dto.request.UserRequestDTO;
import com.SpringUygulama.ToDo.dto.response.TaskResponseDTO;
import com.SpringUygulama.ToDo.dto.response.TokenResponse;
import com.SpringUygulama.ToDo.dto.response.UserResponseDTO;
import com.SpringUygulama.ToDo.entity.User;
import com.SpringUygulama.ToDo.exception.userException.UserNotFoundException;
import com.SpringUygulama.ToDo.exception.userException.UserNotNullException;
import com.SpringUygulama.ToDo.mapper.TaskMapper;
import com.SpringUygulama.ToDo.mapper.UserMapper;
import com.SpringUygulama.ToDo.repository.UserRepository;
import com.SpringUygulama.ToDo.entity.Role;
import com.SpringUygulama.ToDo.util.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;
    private final MailService mailService;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, TaskMapper taskMapper, MailService mailService, UserDetailsServiceImpl userDetailsService, JwtServiceImpl jwtService, AuthenticationManager authManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.taskMapper = taskMapper;
        this.mailService = mailService;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    @Override
    public TokenResponse saveUser(UserRequestDTO userRequestDTO) {
        if(userRequestDTO.getUsername().isBlank()||userRequestDTO.getPassword().isBlank()){
            logger.error("Username and password are null");
            throw new UserNotNullException("Username and password must be not null");
        }
        if (userRepository.existsByUsername(userRequestDTO.getUsername()))
            throw new IllegalArgumentException("Kullanıcı adı kullanımda");
        if (userRepository.existsByEmail(userRequestDTO.getEmail()))
            throw new IllegalArgumentException("Email kullanımda");

        User user=userMapper.mapUserRequestDTOToUser(userRequestDTO);
        user.setVerified(false);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        if(user.getRoles()==null)user.setRoles(Set.of(Role.USER));
        userRepository.save(user);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtService.generateToken(userDetails);

        mailService.sendVerificationEmail(userRequestDTO);

        return TokenResponse.builder().token(token).build();
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        return userMapper.mapUserToUserDTO(userRepository.findById(userId).orElseThrow(()->{
            logger.error("User not found");
            return new UserNotFoundException("User not found exception");
        }));
    }

    @Override
    public List<TaskResponseDTO> getAllTasksByUser(Long id) {
        return taskMapper.mapTasksToTaskDTOs(userRepository.findById(id).orElseThrow(()->{
            logger.error("User not found");
            return new UserNotFoundException("User not found exception");}).getTasks());
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken at =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        authManager.authenticate(at);

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtService.generateToken(userDetails);

        return TokenResponse.builder().token(token).build();
    }
}
