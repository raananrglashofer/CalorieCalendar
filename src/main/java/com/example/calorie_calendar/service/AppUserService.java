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

    public WeeklyTotal getWeeklyTotal(String name){
        Optional<AppUser> user = appUserRepository.findByName(name);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        WeeklyTotal week = user.get().getWeek();
        return week;
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




}
