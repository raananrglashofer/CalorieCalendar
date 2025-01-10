package com.example.calorie_calendar.service;

import com.example.calorie_calendar.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import com.example.calorie_calendar.calendar.*;
import java.util.*;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }

    public List<Activity> findAll(){
        return activityRepository.findAll();
    }

    public void create(Activity activity){
        activityRepository.create(activity);
    }

    public List<Activity> filterByDistance(double distance){
        return activityRepository.filterByDistance(distance);
    }
}
