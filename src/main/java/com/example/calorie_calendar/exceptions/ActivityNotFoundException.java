package com.example.calorie_calendar.exceptions;

public class ActivityNotFoundException extends RuntimeException{

    public ActivityNotFoundException(){
        super("Activity not found");
    }
    public ActivityNotFoundException(String str){
        super(str);
    }
}
