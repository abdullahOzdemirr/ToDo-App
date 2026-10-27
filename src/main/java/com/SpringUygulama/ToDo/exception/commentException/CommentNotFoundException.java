package com.SpringUygulama.ToDo.exception.commentException;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String message){
        super(message);
    }
}
