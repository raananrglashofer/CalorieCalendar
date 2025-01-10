package com.example.calorie_calendar.repository;

import com.example.calorie_calendar.calendar.Activity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.time.*;
import java.util.stream.Collectors;

@Repository
public class ActivityRepository {
    private List<Activity> activities = new ArrayList<>();

    public List<Activity> findAll(){
        return activities;
    }

    public void create(Activity activity){
        activities.add(activity);
    }

    public Optional<List<Activity>> filterByDistance(double distance){
        return Optional.of(activities.stream()
                .filter(activity -> activity.getDistance() >= distance)
                .collect(Collectors.toList()));
    }

    @PostConstruct
    private void init(){
        activities.add(new Activity(30,
            4.0,
                168
        ));

        activities.add(new Activity(60,
                6.0,
                168
        ));

    }




}
