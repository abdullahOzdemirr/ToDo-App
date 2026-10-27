package com.SpringUygulama.ToDo.exception.userException;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
