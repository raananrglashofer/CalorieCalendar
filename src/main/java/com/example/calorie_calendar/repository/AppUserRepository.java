package com.example.calorie_calendar.repository;

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

   @PostConstruct
    private void init(){
       users.add(new AppUser("Raanan",
               168.0,
               AppUser.Gender.MALE,
               70,
               24));

       users.add(new AppUser("Ben",
               150.0,
               AppUser.Gender.MALE,
               70,
               24));
   }
}
