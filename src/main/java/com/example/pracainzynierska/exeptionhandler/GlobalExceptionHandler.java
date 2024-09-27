package com.example.pracainzynierska.exeptionhandler;

import com.example.pracainzynierska.exeption.EmailIsTakenExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailIsTakenExeption.class)
    public ResponseEntity<?> handleEmailIsTaken(EmailIsTakenExeption ex) {
        // Custom response body can be added here, including error details or timestamp
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
