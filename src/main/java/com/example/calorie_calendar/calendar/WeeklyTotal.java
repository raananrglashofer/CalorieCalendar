package com.example.calorie_calendar.calendar;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Entity
public class WeeklyTotal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int totalCalories;
    @OneToOne(mappedBy = "weeklyTotal", fetch = FetchType.LAZY)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "weeklyTotal")
    private List<DailyTotal> days = new ArrayList<>();
    private double totalMiles;
    private int activitiesCount;
    public WeeklyTotal(double bmr, double weight){
        for(DailyTotal.Day day : DailyTotal.Day.values()){
            DailyTotal newDay = new DailyTotal(bmr, day, weight);
            days.add(newDay);
        }
    }

    public void updateCounts(){
        for(DailyTotal dailyTotal : days){
            totalCalories += dailyTotal.getTotalCalories();
            totalMiles += dailyTotal.getMiles();
            activitiesCount += dailyTotal.getActivities().size();
        }
    }

    public DailyTotal getDay(DailyTotal.Day day) {
        for(DailyTotal d : days){
            if(d.getDayOfWeek() == day){
                return d;
            }
        }
        return null;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public double getTotalMiles() {
        return totalMiles;
    }

    public int getActivitiesCount() {
        return activitiesCount;
    }
}
