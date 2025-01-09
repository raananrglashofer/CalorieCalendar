package com.example.calorie_calendar.controller;

import com.example.calorie_calendar.exceptions.UserNotFoundException;
import com.example.calorie_calendar.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.calorie_calendar.calendar.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @GetMapping("")
    List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{name}/week")
    WeeklyTotal displayWeeklyTotal(@PathVariable String name){
        Optional<User> user = userRepository.findByName(name);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        return user.get().getWeek();
    }

    @GetMapping("/{name}")
    User findByName(@PathVariable String name){
        Optional<User> user = userRepository.findByName(name);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        return user.get();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody User user){
        userRepository.create(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{name}")
    void update(@RequestBody User user, @PathVariable String name){
        userRepository.update(user, name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    void delete(@PathVariable String name){
        userRepository.delete(name);
    }


}
