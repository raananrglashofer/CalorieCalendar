package com.example.calorie_calendar.controller;

import com.example.calorie_calendar.dto.AddActivityRequest;
import com.example.calorie_calendar.exceptions.UserNotFoundException;
import com.example.calorie_calendar.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.calorie_calendar.calendar.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
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
        appUserService.updateUser(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("")
    void delete(@RequestBody Long id){
        appUserService.deleteById(id);
    }

    @GetMapping("/{ID}/activities")
    List<Activity> getActivitiesByUser(@PathVariable Long id){
        return appUserService.findAllActivitiesByUserID(id);
    }
    
    @GetMapping("/{id}/activities/filter")
    List<Activity> getActivitiesByUseByDistance(@PathVariable Long id, @RequestBody double distance){
        return appUserService.filterByDistanceByUserID(id, distance);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/activities")
    void addActivityByUser(@PathVariable Long id, @RequestBody AddActivityRequest request){
        appUserService.addActivityByUser(id, request.getActivity(), request.getDay());
    }

    @GetMapping("/{id}/weekly")
    WeeklyTotal displayWeeklyTotal(@PathVariable Long id){
        return appUserService.getWeeklyTotal(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/activities")
    void removeActivityByUser(@PathVariable Long id, @RequestBody Activity activity){
        this.appUserService.removeActivityByUser(id, activity);
    }
}
