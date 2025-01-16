package com.example.calorie_calendar.repository;

import com.example.calorie_calendar.exceptions.UserNotFoundException;
import jakarta.annotation.PostConstruct;

import java.util.*;
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

   public void addActivityByUser(String userName, Activity activity){
       AppUser user = findByName(userName).orElseThrow(UserNotFoundException::new);
       user.addActivity(activity);
   }

   public WeeklyTotal findWeeklyTotalByUser(String userName){
       AppUser user = findByName(userName).orElseThrow(UserNotFoundException::new);
       return user.getWeek();
   }



   @PostConstruct
    private void init(){
       users.add(new AppUser("Raanan",
               168.0,
               AppUser.Gender.MALE,
               70,
               24));
       addActivityByUser("Raanan", new Activity(32, 3.5, findByName("Raanan").get().getWeight()));
       addActivityByUser("Raanan", new Activity(50, 5.3, findByName("Raanan").get().getWeight()));
       addActivityByUser("Raanan", new Activity(70, 7.2, findByName("Raanan").get().getWeight()));
       addActivityByUser("Raanan", new Activity(120, 14.0, findByName("Raanan").get().getWeight()));
       users.get(0).getWeek().getDay(DailyTotal.Day.MONDAY).addActivity(32, 3.5);
       users.get(0).getWeek().getDay(DailyTotal.Day.TUESDAY).addActivity(50, 5.3);
       users.get(0).getWeek().getDay(DailyTotal.Day.THURSDAY).addActivity(70, 7.2);
       users.get(0).getWeek().getDay(DailyTotal.Day.SUNDAY).addActivity(120, 14.0);
       users.get(0).getWeek().updateCounts();

       users.add(new AppUser("Ben",
               150.0,
               AppUser.Gender.MALE,
               70,
               24));

       users.get(1).getWeek().getDay(DailyTotal.Day.MONDAY).addActivity(80, 8.5);
       users.get(1).getWeek().getDay(DailyTotal.Day.TUESDAY).addActivity(50, 5.3);
       users.get(1).getWeek().getDay(DailyTotal.Day.THURSDAY).addActivity(100, 11.2);
       users.get(1).getWeek().getDay(DailyTotal.Day.FRIDAY).addActivity(70, 7.2);
       users.get(1).getWeek().getDay(DailyTotal.Day.SUNDAY).addActivity(200, 22.0);
       users.get(1).getWeek().updateCounts();
   }
}
