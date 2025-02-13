package com.example.calorie_calendar.dto;


import com.example.calorie_calendar.calendar.Activity;
import com.example.calorie_calendar.calendar.Day;

public class AddActivityRequest {
    private long id;
    private Activity activity;
    private Day day;

    // Getters and Setters
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
}
