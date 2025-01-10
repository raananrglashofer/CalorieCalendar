package com.example.calorie_calendar.repository;

import com.example.calorie_calendar.calendar.Activity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ActivityRepository {
    private List<Activity> activities = new ArrayList<>();

    public List<Activity> findAll(){
        return activities;
    }

    public void create(Activity activity){
        activities.add(activity);
    }

    @PostConstruct
    public void init(){
        activities.add(new Activity(.5,
            4.0,


        ));
    }




}
