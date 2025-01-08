package com.example.calorie_calendar.calendar;

import java.util.HashMap;

public class WeeklyTotal {
    private int totalCalories;
    private HashMap<DailyTotal.Day, DailyTotal> days = new HashMap<>();
    private double totalMiles;
    private int activitiesCount;
    public WeeklyTotal(double bmr, double weight){
        for(DailyTotal.Day day : DailyTotal.Day.values()){
            DailyTotal newDay = new DailyTotal(bmr, day, weight);
            days.put(day, newDay);
        }
    }

    public void updateCounts(){
        for(DailyTotal dailyTotal : days.values()){
            totalCalories += dailyTotal.getTotalCalories();
            totalMiles += dailyTotal.getMiles();
            activitiesCount += dailyTotal.getActivities().size();
        }
    }

    public DailyTotal getDay(DailyTotal.Day day) {
        return days.get(day);
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
