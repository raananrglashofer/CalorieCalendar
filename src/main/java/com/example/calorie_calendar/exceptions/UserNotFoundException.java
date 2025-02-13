package com.example.calorie_calendar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String str){
        super(str);
    }
    public UserNotFoundException(){
        super("User not found");
    }
}
