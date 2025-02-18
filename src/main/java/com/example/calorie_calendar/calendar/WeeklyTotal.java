package com.example.calorie_calendar.calendar;


import jakarta.persistence.*;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class WeeklyTotal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private AppUser user;
    private int activeCalories = 0;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "weeklyTotal")
    @JsonIgnore
    private List<DailyTotal> days = new ArrayList<>();
    private double totalMiles = 0;
    private int activitiesCount = 0;
    private int averageCaloriesPerDay = 0;
    public WeeklyTotal() {
        // for database
    }

    public WeeklyTotal(AppUser user){
        this.user = user;
    }

    public DailyTotal getDay(Day curDay) {
        return days.stream()
                .filter(day -> day.getDayOfWeek() == curDay)
                .findFirst()
                .orElse(null);
    }

    public int getActiveCalories() {
        return activeCalories;
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

    public double getWeight(){
        return user.getWeight();
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public void setActiveCalories(int activeCalories) {
        this.activeCalories = activeCalories;
    }
    public void setTotalMiles(double totalMiles) {
        this.totalMiles = totalMiles;
    }
    public void setActivitiesCount(int activitiesCount) {
        this.activitiesCount = activitiesCount;
    }
    public void setAverageCaloriesPerDay(int averageCaloriesPerDay) {
        this.averageCaloriesPerDay = averageCaloriesPerDay;
    }
    public List<DailyTotal> getDays() {
        return days;
    }
}
