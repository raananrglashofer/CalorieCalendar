package com.example.calorie_calendar.service;

import com.example.calorie_calendar.calendar.*;
import com.example.calorie_calendar.exceptions.UserNotFoundException;
import com.example.calorie_calendar.repository.AppUserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> findAll(){
        return appUserRepository.findAll();
    }


    public WeeklyTotal getWeeklyTotal(Long id){
        return appUserRepository.findWeeklyTotalByUserID(id);
    }


    public Optional<AppUser> findByName(String name){
        return appUserRepository.findByName(name);
    }

    public void create(AppUser user){
        appUserRepository.save(user);
    }

    public void deleteById(Long id){
        appUserRepository.deleteById(id);
    }

public void updateUser(AppUser user) {
    AppUser existingUser = appUserRepository.findById(user.getId())
        .orElseThrow(() -> new UserNotFoundException("User with ID " + user.getId() + " not found"));

    // Only update fields if provided (avoid overwriting with null)
    if (user.getName() != null) existingUser.setName(user.getName());
    if (user.getWeight() > 0) existingUser.setWeight(user.getWeight());
    if (user.getHeight() > 0) existingUser.setHeight(user.getHeight());
    if (user.getAge() > 0) existingUser.setAge(user.getAge());
    if (user.getGender() != null) existingUser.setGender(user.getGender());

    existingUser.calculateBMR(); // Recalculate BMR if relevant fields change

    appUserRepository.save(existingUser);
}
    
    public List<Activity> findAllActivitiesByUserID(Long id){
        return appUserRepository.findActivitiesByUserID(id);
    }

    public List<Activity> filterByDistanceByUserID(Long id, double distance){
        return appUserRepository.filterActivitiesByDistance(id, distance);
    }
    @Transactional
    public void addActivityByUser(Long id, Activity activity, Day day){
        AppUser user = this.appUserRepository.findById(id)
                    .orElseThrow(UserNotFoundException::new);
        user.addActivity(activity, day);
        this.appUserRepository.save(user);
    }
    @Transactional
    public void removeActivityByUser(Long id, Activity activity){
        AppUser user = this.appUserRepository.findById(id)
                    .orElseThrow(UserNotFoundException::new);
        user.getActivities().remove(activity);
        this.appUserRepository.save(user);
    }
}
