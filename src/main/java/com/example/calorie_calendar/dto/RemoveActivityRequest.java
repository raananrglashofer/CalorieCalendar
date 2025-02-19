package com.example.calorie_calendar.dto;
import com.example.calorie_calendar.calendar.Activity;

public class RemoveActivityRequest {
    private long id;
    private Activity activity;

    // Getters and Setters
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
}

