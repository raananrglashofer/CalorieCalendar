package com.example.calorie_calendar.service;

import com.example.calorie_calendar.calendar.*;
import com.example.calorie_calendar.exceptions.UserNotFoundException;
import com.example.calorie_calendar.repository.AppUserRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    public AppUser create(AppUser user) {
        // Create WeeklyTotal and associate it with user
        WeeklyTotal weeklyTotal = new WeeklyTotal();
        weeklyTotal.setUser(user); // Ensure user_id is set in WeeklyTotal
    
        user.setWeek(weeklyTotal);
        
        return appUserRepository.save(user);
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
        WeeklyTotal week = user.getWeek();
        DailyTotal dailyTotal = week.getDay(day);
        // update activity
        activity.setDailyTotal(dailyTotal);
        activity.setDistance(activity.getDistance());
        activity.setDuration(activity.getTime());
        activity.setWeight(week.getWeight());
        int calories = activity.getCaloriesBurned();
        processActivity(activity);
        // update DailyTotal
        dailyTotal.setTotalCalories(dailyTotal.getTotalCalories() + calories);
        dailyTotal.setActivityCalories(dailyTotal.getActivityCalories() + calories);
        dailyTotal.setMiles(dailyTotal.getMiles() + activity.getDistance());
        dailyTotal.getActivities().add(activity);
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

    private void processActivity(Activity activity){
        setMet(activity);
        setCaloriesForWorkout(activity);
    }
    // Calories Burned = MET x Body Weight (kg) x Duration of Running (hours)
    private void setCaloriesForWorkout(Activity activity){
        double durationInHours = activity.getTime() / 60.0;
        double kilograms = activity.getWeight()/2.2;
        int calories = (int) (activity.getMET() * kilograms * durationInHours);
        activity.setCaloriesBurned(calories);
    }

    private void setMet(Activity activity){
        double durationInHours = activity.getTime() / 60.0;
        double speed = activity.getDistance() / durationInHours; // this math could be wrong
        String fileName = "src/main/resources/met.csv";
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException("MET file not found: " + fileName);
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            double met = 0;
            while((line = br.readLine()) != null && met == 0){
                String[] split = line.split(",");
                double tempo = Double.parseDouble(split[0]);
                if(speed >= tempo && speed < tempo + 1){
                    double curMet = Double.parseDouble(split[1]);
                    met = curMet;
                } else if(speed > 9.0){ // placeholder until i update the csv file for mets 
                    met = 12.8;
                }
            }
            activity.setMet(met);
        } catch(Exception e){
            throw new RuntimeException();
        } 
    }
}
