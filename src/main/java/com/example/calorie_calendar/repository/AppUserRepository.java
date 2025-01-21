package com.example.calorie_calendar.repository;

import com.example.calorie_calendar.exceptions.UserNotFoundException;
import jakarta.annotation.PostConstruct;

import java.util.*;
import java.util.stream.Collectors;

import com.example.calorie_calendar.calendar.*;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserRepository {

    private List<AppUser> users = new ArrayList<>();

   public List<AppUser> findAll(){
       return users;
   }

   public Optional<AppUser> findByName(String name){
       return users.stream()
               .filter(user -> user.getName().equals(name))
               .findFirst();
   }

   public void create(AppUser user){
       users.add(user);
   }

   public void update(AppUser user){
       Optional<AppUser> existingUser = findByName(user.getName());
       if(existingUser.isPresent()){
           users.set(users.indexOf(existingUser.get()), user);
       }
   }
   public void delete(String name){
       System.out.println(name);
       users.removeIf(user -> user.getName().equals(name));
   }

   public List<Activity> findActivitiesByUser(String userName){
       AppUser user = findByName(userName).orElseThrow(UserNotFoundException::new);
       return user.getActivities();
   }

   public void addActivityByUser(String userName, Activity activity, Day day){
       AppUser user = findByName(userName).orElseThrow(UserNotFoundException::new);
       user.addActivity(activity, day);
   }

   public WeeklyTotal findWeeklyTotalByUser(String userName){
       AppUser user = findByName(userName).orElseThrow(UserNotFoundException::new);
       return user.getWeek();
   }

   public List<Activity> filterActivitiesByDistance(String userName, double distance){
       return findActivitiesByUser(userName).stream()
               .filter(activity -> activity.getDistance() >= distance)
               .collect(Collectors.toList());
   }

    public void removeActivityByUser(String userName, Activity activity){
        findActivitiesByUser(userName).remove(activity);
    }

   @PostConstruct
    private void init(){
       users.add(new AppUser("Raanan",
               168.0,
               AppUser.Gender.MALE,
               70,
               24));
       addActivityByUser("Raanan", new Activity(32, 3.5, findByName("Raanan").get().getWeight()), Day.MONDAY);
       addActivityByUser("Raanan", new Activity(50, 5.3, findByName("Raanan").get().getWeight()), Day.TUESDAY);
       addActivityByUser("Raanan", new Activity(70, 7.2, findByName("Raanan").get().getWeight()), Day.THURSDAY);
       addActivityByUser("Raanan", new Activity(120, 14.0, findByName("Raanan").get().getWeight()), Day.SUNDAY);

       users.add(new AppUser("Ben",
               150.0,
               AppUser.Gender.MALE,
               70,
               24));

       addActivityByUser("Ben", new Activity(80, 8.5, findByName("Ben").get().getWeight()), Day.MONDAY);
       addActivityByUser("Ben", new Activity(50, 5.3, findByName("Ben").get().getWeight()), Day.TUESDAY);
       addActivityByUser("Ben", new Activity(100, 11.2, findByName("Ben").get().getWeight()), Day.THURSDAY);
       addActivityByUser("Ben", new Activity(70, 7.2, findByName("Ben").get().getWeight()), Day.THURSDAY);
       addActivityByUser("Ben", new Activity(200, 22.0, findByName("Ben").get().getWeight()), Day.SUNDAY);
   }
}
