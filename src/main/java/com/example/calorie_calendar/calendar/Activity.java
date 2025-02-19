package com.example.calorie_calendar.calendar;


import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_total_id")
    private DailyTotal dailyTotal;
    private int duration = 0; // in minutes
    private double distance = 0;
    private int caloriesBurned = 0;
    private double met = 0;
    public Activity(){
        // for database
    }

    // public Activity(int duration, double distance, double weight){
    //     if(duration <= 0 || distance <= 0 || weight <= 0){
    //         throw new IllegalArgumentException("Time, Length, Speed, or Weight are not a positive number");
    //     }
    //     setDuration(duration);
    //     setDistance(distance);
    //     setWeight(weight);
    // }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public int getTime() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }
    public double getMET(){
        return met;
    }

    public void setDistance(double distance){
        this.distance = distance;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public void setCaloriesBurned(int caloriesBurned){
        this.caloriesBurned = caloriesBurned;
    }

    public void setDailyTotal(DailyTotal dailyTotal){
        this.dailyTotal = dailyTotal;
    }

    public void setMet(double met){
        this.met = met;
    }
    public long getId(){
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Double.compare(activity.distance, distance) == 0 && duration == activity.duration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, distance);
    }
}
