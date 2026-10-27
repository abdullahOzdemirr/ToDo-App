package com.SpringUygulama.ToDo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    //private List<String> details;
    private  String  detail;
    private LocalDateTime timestampt;
}
