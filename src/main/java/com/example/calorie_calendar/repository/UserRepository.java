package com.example.calorie_calendar.repository;

import com.example.calorie_calendar.calendar.WeeklyTotal;
import jakarta.annotation.PostConstruct;

import java.util.*;
import com.example.calorie_calendar.calendar.*;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

   public List<User> findAll(){
       return users;
   }

   public Optional<User> findByName(String name){
       return users.stream()
               .filter(user -> user.getName().equals(name))
               .findFirst();
   }

   public void create(User user){
       users.add(user);
   }

   public void update(User user, String name){
       Optional<User> existingUser = findByName(name);
       if(existingUser.isPresent()){
           users.set(users.indexOf(existingUser.get()), user);
       }
   }
   public void delete(String name){
       users.removeIf(user -> user.getName().equals(name));
   }

   @PostConstruct
    private void init(){
       users.add(new User("Raanan",
               168.0,
               User.Gender.MALE,
               70,
               24));

       users.add(new User("Ben",
               150.0,
               User.Gender.MALE,
               70,
               24));
   }
}
