package com.example.calorie_calendar.service;

import com.example.calorie_calendar.calendar.DailyTotal;
import com.example.calorie_calendar.calendar.WeeklyTotal;
import com.example.calorie_calendar.repository.AppUserRepository;
import com.example.calorie_calendar.repository.SummaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryService {
    private final AppUserRepository appUserRepository;

    public SummaryService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public WeeklyTotal findWeeklyForUser(String userName){
        return appUserRepository.findWeeklyTotalByUser(userName);
    }
}
