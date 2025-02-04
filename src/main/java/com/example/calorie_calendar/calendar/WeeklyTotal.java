package com.example.calorie_calendar.calendar;


import jakarta.persistence.*;

import java.util.*;

@Entity
public class WeeklyTotal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(mappedBy = "weeklyTotal", fetch = FetchType.LAZY)
    private AppUser user;
    private int totalCalories;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "weeklyTotal")
    private Map<Day, DailyTotal> days = new HashMap<>();
    private double totalMiles;
    private int activitiesCount;
    private int averageCaloriesPerDay;
    private double bmr;
    public WeeklyTotal(double bmr, double weight){
        for(Day day : Day.values()){
            DailyTotal newDay = new DailyTotal(bmr, day, weight);
            days.put(day, newDay);
        }
        this.bmr = bmr;
    }

    public void updateCounts(Activity activity){
        totalCalories += activity.getCaloriesBurned();
        totalMiles += activity.getDistance();
        activitiesCount++;
        averageCaloriesPerDay = (int) ((totalCalories/7) + bmr);
    }

    public DailyTotal getDay(Day day) {
        return days.get(day);
    }

    public void addActivityToDay(Activity activity, Day day){
        days.get(day).addActivity(activity);
        updateCounts(activity);
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
    public int getAverageCaloriesPerDay() {
        return averageCaloriesPerDay;
    }
}
