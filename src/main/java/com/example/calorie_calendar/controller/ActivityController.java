package com.example.calorie_calendar.controller;

import com.example.calorie_calendar.calendar.Activity;
import com.example.calorie_calendar.exceptions.ActivityNotFoundException;
import com.example.calorie_calendar.repository.ActivityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityRepository activityRepository;

    public ActivityController(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    @GetMapping("")
    List<Activity> findAll(){
        return activityRepository.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Activity activity){
        activityRepository.create(activity);
    }

    @GetMapping("/filter")
    Optional<List<Activity>> filterByDistance(@RequestBody double distance){
        Optional<List<Activity>> activities = activityRepository.filterByDistance(distance);
        if(activities.isEmpty()){
            throw new ActivityNotFoundException();
        }
        return activities;
    }


}
