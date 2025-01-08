package com.example.calorie_calendar.controller;

import com.example.calorie_calendar.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.calorie_calendar.calendar.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class CalendarController {
    private final UserRepository userRepository;

    public CalendarController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @GetMapping("")
    List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{name}")
    User findByName(@PathVariable String name){
        Optional<User> user = userRepository.findByName(name);
        if(user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    @PostMapping
    void create(@RequestBody User user){
        userRepository.create(user);
    }
}
