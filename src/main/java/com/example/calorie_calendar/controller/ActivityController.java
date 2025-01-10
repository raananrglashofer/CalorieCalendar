package com.example.calorie_calendar.controller;

import com.example.calorie_calendar.calendar.Activity;
import com.example.calorie_calendar.exceptions.ActivityNotFoundException;
import com.example.calorie_calendar.repository.ActivityRepository;
import com.example.calorie_calendar.service.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }

    @GetMapping("")
    List<Activity> findAll(){
        return activityService.findAll();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Activity activity){
        activityService.create(activity);
    }

    @GetMapping("/filter")
    List<Activity> filterByDistance(@RequestBody double distance){
        List<Activity>activities = activityService.filterByDistance(distance);
        if(activities.isEmpty()){
            throw new ActivityNotFoundException();
        }
        return activities;
    }


}
