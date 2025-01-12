package com.example.calorie_calendar.service;

import com.example.calorie_calendar.calendar.DailyTotal;
import com.example.calorie_calendar.calendar.WeeklyTotal;
import com.example.calorie_calendar.repository.SummaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SummaryService {
    private final SummaryRepository summaryRepository;

    public SummaryService(SummaryRepository summaryRepository){
        this.summaryRepository = summaryRepository;
    }

    public List<WeeklyTotal> findAllWeekly(){
        return summaryRepository.findAllWeekly();
    }

    public List<DailyTotal> findAllDaily(){
        return summaryRepository.findAllDaily();
    }
}
