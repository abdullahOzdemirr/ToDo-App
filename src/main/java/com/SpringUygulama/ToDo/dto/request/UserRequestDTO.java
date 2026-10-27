package com.SpringUygulama.ToDo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @Email(message = "hatalı email girildi")
    private String email;
    @Min(value = 13,message = "küçüksün")
    private int age;
    private Set<String> roles;
}
