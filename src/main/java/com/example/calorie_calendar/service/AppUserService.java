package com.example.calorie_calendar.service;

import com.example.calorie_calendar.calendar.*;
import com.example.calorie_calendar.calendar.AppUser.Gender;
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
        for(Day day : Day.values()){
            DailyTotal newDay = new DailyTotal(user.getBmr(), day);
            newDay.setWeek(weeklyTotal);
            weeklyTotal.getDays().add(newDay);
        }
        weeklyTotal.setUser(user); // Ensure user_id is set in WeeklyTotal
        user.setWeek(weeklyTotal);

        calculateBMR(user);
        return appUserRepository.save(user);
    }
   // For men: ((9.65 × (weight in lb/2.2)) + (573 × (height in inches * .0254)) – (5.08 × age in years) + 260) * 1.2
   // For women: ((7.38 × (weight in lb/2.2)) + (607 × (height in inches * .0254)) – (2.31 × age in years) + 43) * 1.2
    private void calculateBMR(AppUser user){
        double bmr = 0;
        if(user.getGender() == Gender.MALE){
            bmr = (((9.65 * (user.getWeight()/2.2)) + (573 * (user.getHeight() * .0254)) - (5.08 * user.getAge()) + 260) * 1.2);
        } else if(user.getGender() == Gender.FEMALE){
            bmr = (((7.38 * (user.getWeight()/2.2)) + (607 * (user.getHeight() * .0254)) - (2.31 * user.getAge()) + 43) * 1.2);
        }
        user.setBmr(bmr);
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

    calculateBMR(existingUser); // Recalculate BMR if relevant fields change

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
        processActivity(user, activity);
        int calories = activity.getCaloriesBurned();
        // update DailyTotal
        dailyTotal.setTotalCalories(dailyTotal.getTotalCalories() + calories);
        dailyTotal.setActivityCalories(dailyTotal.getActivityCalories() + calories);
        dailyTotal.setMiles(dailyTotal.getMiles() + activity.getDistance());
        dailyTotal.getActivities().add(activity);
        // update WeeklyTotal
        week.setActiveCalories(week.getActiveCalories() + calories);
        week.setTotalMiles(week.getTotalMiles() + activity.getDistance());
        week.setActivitiesCount(week.getActivitiesCount() + 1);
        week.setAverageCaloriesPerDay((int) ((week.getActiveCalories()/7) + user.getBmr()));
        // update user
        user.getActivities().add(activity);
        this.appUserRepository.save(user);
    }
    @Transactional
    public void removeActivityByUser(Long id, Activity activity){
        AppUser user = this.appUserRepository.findById(id)
                    .orElseThrow(UserNotFoundException::new);
        user.getActivities().remove(activity);
        this.appUserRepository.save(user);
    }

    private void processActivity(AppUser user, Activity activity){
        setMet(activity);
        setCaloriesForWorkout(user, activity);
    }
    // Calories Burned = MET x Body Weight (kg) x Duration of Running (hours)
    private void setCaloriesForWorkout(AppUser user, Activity activity){
        double durationInHours = activity.getTime() / 60.0;
        double kilograms = user.getWeight()/2.2;
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
