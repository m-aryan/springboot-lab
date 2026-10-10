package com.practice.lab.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidCaseStateException extends RuntimeException {

    public InvalidCaseStateException(String message) {
        super(message);
    }
}
