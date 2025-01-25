package com.NganTrung.ToDoList;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ToDoExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<String> ArgumentNotValid(MethodArgumentNotValidException ex){
        return ResponseEntity.badRequest().body("Something goes wrong!!");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> DataNotValid(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something goes wrong!!");
    }
}
