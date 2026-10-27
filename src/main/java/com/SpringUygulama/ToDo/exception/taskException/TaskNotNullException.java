package com.SpringUygulama.ToDo.exception.taskException;

public class TaskNotNullException extends RuntimeException{
    public TaskNotNullException(String message){
        super(message);
    }
}
