package com.example.calorie_calendar.calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.*;

public class DailyTotal {
    private final LocalDate date;
    private List<Activity> activities = new ArrayList<>();
    private int totalCalories;
    private int activityCalories;
    private final Day dayOfWeek;
    private double miles;
    private double weight;
    enum Day {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}

    public DailyTotal(double bmr, Day dayOfWeek, double weight){
        this.date = LocalDate.now();
        this.totalCalories += bmr;
        this.dayOfWeek = dayOfWeek;
        this.weight = weight;
    }

    public void addActivity(int duration, double distance, double speed){
        Activity activity = new Activity(duration, distance, weight);
        int calories = activity.getCaloriesBurned();
        this.totalCalories += calories;
        this.activityCalories += calories;
        this.activities.add(activity);
        this.miles += distance;
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
}
