package com.example.calorie_calendar.repository;

import com.example.calorie_calendar.calendar.DailyTotal;
import com.example.calorie_calendar.calendar.Day;
import com.example.calorie_calendar.calendar.WeeklyTotal;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
@Repository
public class SummaryRepository {
    private List<WeeklyTotal> weeklyTotals = new ArrayList<>();
    private List<DailyTotal> dailyTotals = new ArrayList<>();

    public List<WeeklyTotal> findAllWeekly(){
        return weeklyTotals;
    }

    public List<DailyTotal> findAllDaily(){
        return dailyTotals;
    }
    @PostConstruct
    private void init(){
        weeklyTotals.add(new WeeklyTotal(2272,
                168.0
        ));

        dailyTotals.add(new DailyTotal(2272,
                Day.MONDAY,
                168.0
                ));
        dailyTotals.add(new DailyTotal(2272,
                Day.TUESDAY,
                168.0
        ));
        dailyTotals.add(new DailyTotal(2272,
                Day.WEDNESDAY,
                168.0
        ));
        dailyTotals.add(new DailyTotal(2272,
                Day.THURSDAY,
                168.0
        ));
        dailyTotals.add(new DailyTotal(2272,
                Day.FRIDAY,
                168.0
        ));
        dailyTotals.add(new DailyTotal(2272,
                Day.SATURDAY,
                168.0
        ));
        dailyTotals.add(new DailyTotal(2272,
                Day.SUNDAY,
                168.0
        ));


        weeklyTotals.add(new WeeklyTotal(2177,
                150.0
        ));

        dailyTotals.add(new DailyTotal(2177,
                Day.MONDAY,
                150.0
        ));
        dailyTotals.add(new DailyTotal(2177,
                Day.TUESDAY,
                150.0
        ));
        dailyTotals.add(new DailyTotal(2177,
                Day.WEDNESDAY,
                150.0
        ));
        dailyTotals.add(new DailyTotal(2177,
                Day.THURSDAY,
                150.0
        ));
        dailyTotals.add(new DailyTotal(2177,
                Day.FRIDAY,
                150.0
        ));
        dailyTotals.add(new DailyTotal(2177,
                Day.SATURDAY,
                150.0
        ));
        dailyTotals.add(new DailyTotal(2177,
                Day.SUNDAY,
                150.0
        ));
    }
}
