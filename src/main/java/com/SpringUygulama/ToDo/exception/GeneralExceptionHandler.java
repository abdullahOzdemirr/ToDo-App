package com.SpringUygulama.ToDo.exception;

import com.SpringUygulama.ToDo.exception.categoryException.CategoryNotFoundException;
import com.SpringUygulama.ToDo.exception.categoryException.CategoryNotNullException;
import com.SpringUygulama.ToDo.exception.commentException.CommentNotFoundException;
import com.SpringUygulama.ToDo.exception.commentException.CommentNotNullException;
import com.SpringUygulama.ToDo.exception.taskException.TaskNotFoundException;
import com.SpringUygulama.ToDo.exception.taskException.TaskNotNullException;
import com.SpringUygulama.ToDo.exception.userException.UserNotFoundException;
import com.SpringUygulama.ToDo.exception.userException.UserNotNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UserNotNullException.class)
    public ResponseEntity<ErrorResponse> userNotNull(UserNotNullException userNotNullException){
        return new ResponseEntity<>(new ErrorResponse("Add username",userNotNullException.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFound(UserNotFoundException userNotFoundException){
        return new ResponseEntity<>(new ErrorResponse("User not found exception handler",userNotFoundException.getMessage(),LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TaskNotNullException.class)
    public ResponseEntity<ErrorResponse> taskNotNull(TaskNotNullException taskNotNullException){
        return new ResponseEntity<>(new ErrorResponse("Add task body",taskNotNullException.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> taskNotFound(TaskNotFoundException taskNotFoundException){
        return new ResponseEntity<>(new ErrorResponse("Task not found exception handler",taskNotFoundException.getMessage(),LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CommentNotNullException.class)
    public ResponseEntity<ErrorResponse> commentNotNull(CommentNotNullException commentNotNullException){
        return new ResponseEntity<>(new ErrorResponse("Add comment body",commentNotNullException.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> taskNotFound(CommentNotFoundException commentNotFoundException){
        return new ResponseEntity<>(new ErrorResponse("Comment not found exception handler",commentNotFoundException.getMessage(),LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CategoryNotNullException.class)
    public ResponseEntity<ErrorResponse> categoryNotNull(CategoryNotNullException categoryNotNullException){
        return new ResponseEntity<>(new ErrorResponse("Add category body",categoryNotNullException.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> taskNotFound(CategoryNotFoundException categoryNotFoundException){
        return new ResponseEntity<>(new ErrorResponse("Category not found exception handler",categoryNotFoundException.getMessage(),LocalDateTime.now()),HttpStatus.BAD_REQUEST);
    }
}
