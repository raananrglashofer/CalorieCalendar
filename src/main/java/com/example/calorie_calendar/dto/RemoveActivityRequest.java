package com.example.calorie_calendar.dto;
import com.example.calorie_calendar.calendar.*;

public class RemoveActivityRequest {
    private long id;
    private Day day;
    private long activityId;

    // Getters and Setters

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
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

