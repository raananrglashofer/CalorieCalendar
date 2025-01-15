package com.example.calorie_calendar.controller;

import com.example.calorie_calendar.exceptions.UserNotFoundException;
import com.example.calorie_calendar.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.calorie_calendar.calendar.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }
    @GetMapping("")
    List<AppUser> findAll(){
        return appUserService.findAll();
    }

    @GetMapping("/{name}/week")
    WeeklyTotal displayWeeklyTotal(@PathVariable String name){
        return appUserService.getWeeklyTotal(name);
    }

    @GetMapping("/{name}")
    AppUser findByName(@PathVariable String name){
        Optional<AppUser> user = appUserService.findByName(name);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        return user.get();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody AppUser user){
        appUserService.create(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("")
    void update(@RequestBody AppUser user){
        appUserService.update(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("")
    void delete(@RequestBody String name){
        appUserService.delete(name);
    }


}
