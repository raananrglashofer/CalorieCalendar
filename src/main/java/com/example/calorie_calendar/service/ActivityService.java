package com.example.calorie_calendar.service;

import com.example.calorie_calendar.repository.ActivityRepository;
import com.example.calorie_calendar.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import com.example.calorie_calendar.calendar.*;
import java.util.*;

@Service
public class ActivityService {
    private final AppUserRepository appUserRepository;

    public ActivityService(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    public List<Activity> findAllByUser(String userName){
        return appUserRepository.findActivitiesByUser(userName);
    }

    public void create(String userName, Activity activity, Day day){
        appUserRepository.addActivityByUser(userName, activity, day);
    }

    public List<Activity> filterByDistanceByUser(String userName, double distance){
        return appUserRepository.filterActivitiesByDistance(userName, distance);
    }
}
