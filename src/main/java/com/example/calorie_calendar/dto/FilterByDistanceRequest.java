package com.example.calorie_calendar.dto;

public class FilterByDistanceRequest{
    private long Id;
    private double distance;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}