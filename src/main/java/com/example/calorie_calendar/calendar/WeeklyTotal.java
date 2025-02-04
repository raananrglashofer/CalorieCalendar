package com.example.calorie_calendar.calendar;


import jakarta.persistence.*;

import java.util.*;

@Entity
public class WeeklyTotal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private AppUser user;
    private int totalCalories;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "weeklyTotal")
    private Map<Day, DailyTotal> days = new HashMap<>();
    private double totalMiles;
    private int activitiesCount;
    private int averageCaloriesPerDay;
    private double bmr;
    public WeeklyTotal() {
        // for database
    }
    public WeeklyTotal(AppUser user){
        for(Day day : Day.values()){
            DailyTotal newDay = new DailyTotal(bmr, day, user.getWeight());
            days.put(day, newDay);
        }
        this.bmr = user.getBmr();
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
