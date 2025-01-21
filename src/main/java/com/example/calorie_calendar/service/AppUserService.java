package com.example.calorie_calendar.service;

import com.example.calorie_calendar.calendar.*;
import com.example.calorie_calendar.exceptions.UserNotFoundException;
import com.example.calorie_calendar.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    public WeeklyTotal getWeeklyTotal(String userName){
        return appUserRepository.findWeeklyTotalByUser(userName);
    }

    public List<AppUser> findAll(){
        return appUserRepository.findAll();
    }

    public Optional<AppUser> findByName(String name){
        return appUserRepository.findByName(name);
    }

    public void create(AppUser user){
        appUserRepository.create(user);
    }

    public void delete(String name){
        appUserRepository.delete(name);
    }

    public void update(AppUser user){
        appUserRepository.update(user);
    }
    public List<Activity> findAllActivitiesByUser(String userName){
        return appUserRepository.findActivitiesByUser(userName);
    }

    public List<Activity> filterByDistanceByUser(String userName, double distance){
        return appUserRepository.filterActivitiesByDistance(userName, distance);
    }

    public void addActivityByUser(String userName, Activity activity, Day day){
        this.appUserRepository.addActivityByUser(userName, activity, day);
    }

    public void removeActivityByUser(String userName, Activity activity){
        this.appUserRepository.removeActivityByUser(userName, activity);
    }
}
