package com.SpringUygulama.ToDo.exception.categoryException;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String message){
        super(message);
    }
}
