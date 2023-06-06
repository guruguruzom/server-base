package com.example.restfulwebservoce.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {//사용시 발생하는 오류 {
    public UserNotFoundException(String message) {
        super(message);
    }
}
