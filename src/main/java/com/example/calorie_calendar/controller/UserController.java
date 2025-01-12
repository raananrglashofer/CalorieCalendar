package com.example.calorie_calendar.controller;

import com.example.calorie_calendar.exceptions.UserNotFoundException;
import com.example.calorie_calendar.repository.UserRepository;
import com.example.calorie_calendar.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.calorie_calendar.calendar.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("")
    List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{name}/week")
    WeeklyTotal displayWeeklyTotal(@PathVariable String name){
        return userService.getWeeklyTotal(name);
    }

    @GetMapping("/{name}")
    User findByName(@PathVariable String name){
        Optional<User> user = userService.findByName(name);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        return user.get();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody User user){
        userService.create(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("")
    void update(@RequestBody User user){
        userService.update(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("")
    void delete(@RequestBody String name){
        userService.delete(name);
    }


}
