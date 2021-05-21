package com.selflearning.englishcourses.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RegistrationControllerAdvice {

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Void> handleException(){
//        return ResponseEntity.badRequest().body(null);
//    }

}
