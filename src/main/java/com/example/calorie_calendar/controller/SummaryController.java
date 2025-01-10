package com.example.calorie_calendar.controller;

import com.example.calorie_calendar.calendar.DailyTotal;
import com.example.calorie_calendar.calendar.WeeklyTotal;
import com.example.calorie_calendar.repository.SummaryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
@RestController
@RequestMapping("/api/summaries")
public class SummaryController {
    private final SummaryRepository summaryRepository;

    public SummaryController(SummaryRepository summaryRepository){
        this.summaryRepository = summaryRepository;
    }

    @GetMapping("")
    String home(){
        return "Daily or Weekly?";
    }

    @GetMapping("/weekly")
    List<WeeklyTotal> findAllWeekly(){
        return summaryRepository.findAllWeekly();
    }

    @GetMapping("/daily")
    List<DailyTotal> findAllDaily(){
        return summaryRepository.findAllDaily();
    }
}
