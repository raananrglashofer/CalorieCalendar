package com.example.calorie_calendar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {
    @GetMapping("/hello")
    String home(){
        return "hello calendar!";
    } 
}
