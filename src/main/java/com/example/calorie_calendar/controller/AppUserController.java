package com.example.calorie_calendar.controller;

import com.example.calorie_calendar.dto.*;
import com.example.calorie_calendar.exceptions.UserNotFoundException;
import com.example.calorie_calendar.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.calorie_calendar.calendar.*;

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

    @GetMapping("/activities")
    List<Activity> getActivitiesByUser(@RequestBody Long id){
        Optional<AppUser> user = appUserService.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        return appUserService.findAllActivitiesByUserID(id);
    }
    
    @GetMapping("/activities/filter")
    List<Activity> getActivitiesByUserByDistance(@RequestBody FilterByDistanceRequest request){
        return appUserService.filterByDistanceByUserID(request.getId(), request.getDistance());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/activities")
    void addActivityByUser(@RequestBody AddActivityRequest request){
        appUserService.addActivityByUser(request.getId(), request.getActivity(), request.getDay());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/activities")
    void removeActivityByUser(@RequestBody RemoveActivityRequest request){
        this.appUserService.removeActivityByUser(request.getId(), request.getActivityId(), request.getDay());
    }

    @GetMapping("/weekly")
    WeeklyTotal displayWeeklyTotal(@RequestBody Long id){
        return appUserService.getWeeklyTotal(id);
    }
}
