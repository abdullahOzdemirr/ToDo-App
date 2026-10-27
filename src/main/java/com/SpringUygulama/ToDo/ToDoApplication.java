package com.SpringUygulama.ToDo;

import com.SpringUygulama.ToDo.entity.User;
import com.SpringUygulama.ToDo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ToDoApplication {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ToDoApplication(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(ToDoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            if (userRepository.findByUsername("testuser").isEmpty()) {
                User user = new User();
                user.setUsername("testuser");
                user.setPassword(passwordEncoder.encode("123456"));
                user.setAge(20);
                user.setVerified(true);
                user.setEmail("testuser@example.com");
                user.setTasks(new ArrayList<>());
                user.setComments(new ArrayList<>());

                userRepository.save(user);
            }
        };
    }


}
