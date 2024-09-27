package com.example.pracainzynierska.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Email is taken")
public class EmailIsTakenExeption extends RuntimeException {
    public EmailIsTakenExeption(String message) {
        super(message);
    }

}
