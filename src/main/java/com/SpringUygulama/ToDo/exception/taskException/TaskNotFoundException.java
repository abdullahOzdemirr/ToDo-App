package com.SpringUygulama.ToDo.exception.taskException;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(String message){
        super(message);
    }
}
