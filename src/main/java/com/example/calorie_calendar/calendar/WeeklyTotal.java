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
    private int activeCalories = 0;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "weeklyTotal")
    private List<DailyTotal> days = new ArrayList<>();
    private double totalMiles = 0;
    private int activitiesCount = 0;
    private int averageCaloriesPerDay = 0;
    private double bmr = 0;
    public WeeklyTotal() {
        // for database
        for(Day day : Day.values()){
            DailyTotal newDay = new DailyTotal(bmr, day);
            days.add(newDay);
        }
    }

    public WeeklyTotal(AppUser user){
        for(Day day : Day.values()){
            DailyTotal newDay = new DailyTotal(bmr, day);
            days.add(newDay);
        }
        this.bmr = user.getBmr();
    }

    public void updateCounts(Activity activity){
        activeCalories += activity.getCaloriesBurned();
        totalMiles += activity.getDistance();
        activitiesCount++;
        averageCaloriesPerDay = (int) ((activeCalories/7) + bmr);
    }

    public DailyTotal getDay(Day curDay) {
        for(int i = 0; i < days.size(); i++){
            if(curDay == days.get(i).getDayOfWeek()){
                return days.get(i);
            }

        }
        return null;
    }

    public void addActivityToDay(Activity activity, Day day){
        getDay(day).addActivity(activity);
        updateCounts(activity);
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
}
