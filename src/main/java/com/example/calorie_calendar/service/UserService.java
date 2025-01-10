package com.example.calorie_calendar.service;

import com.example.calorie_calendar.calendar.*;
import com.example.calorie_calendar.exceptions.UserNotFoundException;
import com.example.calorie_calendar.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public WeeklyTotal getWeeklyTotal(String name){
        Optional<User> user = userRepository.findByName(name);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        WeeklyTotal week = user.get().getWeek();
        return week;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findByName(String name){
        return userRepository.findByName(name);
    }

    public void create(User user){
        userRepository.create(user);
    }

    public void delete(String name){
        userRepository.delete(name);
    }

    public void update(User user){
        userRepository.update(user);
    }




}
