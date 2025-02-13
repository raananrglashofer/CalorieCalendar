package com.example.calorie_calendar.calendar;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class DailyTotal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weekly_total_id")
    private WeeklyTotal weeklyTotal;
    private final LocalDate date;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "dailyTotal")
    private List<Activity> activities = new ArrayList<>();
    private int totalCalories;
    private int activityCalories;
    @Enumerated(EnumType.STRING)
    private Day dayOfWeek = null;
    private double miles;

    public DailyTotal(){
        this.date = LocalDate.now();
    }

    public DailyTotal(double bmr, Day dayOfWeek){
        this.date = LocalDate.now();
        this.totalCalories += bmr;
        this.dayOfWeek = dayOfWeek;
    }

    public void addActivity(Activity activity){
        int calories = activity.getCaloriesBurned();
        this.totalCalories += calories;
        this.activityCalories += calories;
        this.activities.add(activity);
        this.miles += activity.getDistance();
    }

    public LocalDate getDate() {
        return date;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public Day getDayOfWeek() {
        return dayOfWeek;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public double getMiles(){
        return miles;
    }
    public int getActivityCalories() {
        return activityCalories;
    }
}
