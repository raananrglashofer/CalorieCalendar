package com.example.calorie_calendar.controller;

import com.example.calorie_calendar.calendar.DailyTotal;
import com.example.calorie_calendar.calendar.WeeklyTotal;
import com.example.calorie_calendar.repository.SummaryRepository;
import com.example.calorie_calendar.service.SummaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
@RestController
@RequestMapping("/api/summaries")
public class SummaryController {
    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService){
        this.summaryService = summaryService;
    }

    @GetMapping("")
    String home(){
        return "Daily or Weekly?";
    }

    @GetMapping("/weekly")
    List<WeeklyTotal> findAllWeekly(){
        return summaryService.findAllWeekly();
    }

    @GetMapping("/daily")
    List<DailyTotal> findAllDaily(){
        return summaryService.findAllDaily();
    }
}
