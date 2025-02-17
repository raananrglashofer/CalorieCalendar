package com.example.calorie_calendar.calendar;


import jakarta.persistence.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    private double weight = 0;
    public Activity(){
        // for database
    }

    public Activity(int duration, double distance, double weight){
        if(duration <= 0 || distance <= 0 || weight <= 0){
            throw new IllegalArgumentException("Time, Length, Speed, or Weight are not a positive number");
        }
        setDuration(duration);
        setDistance(distance);
        setWeight(weight);
        setMet();
        calculateCaloriesBurned();
    }

    public int getCaloriesBurned() {
        calculateCaloriesBurned();
        return caloriesBurned;
    }

    public int getTime() {
        return duration;
    }

    public double getDistance() {
        return distance;
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

    public void setWeight(double weight){
        this.weight = weight;
    }

    public void setMet(){
        double durationInHours = duration / 60.0;
        double speed = distance / durationInHours; // this math could be wrong
        String fileName = "src/main/resources/met.csv";
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException("MET file not found: " + fileName);
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null && this.met == 0){
                String[] split = line.split(",");
                double tempo = Double.parseDouble(split[0]);
                if(speed >= tempo && speed < tempo + 1){
                    double curMet = Double.parseDouble(split[1]);
                    this.met = curMet;
                } else if(speed > 9.0){ // placeholder until i update the csv file for mets 
                    this.met = 12.8;
                }
            }
        } catch(Exception e){
            throw new RuntimeException();
        }
    }

    // Calories Burned = MET x Body Weight (kg) x Duration of Running (hours)
    public void calculateCaloriesBurned(){
        double durationInHours = duration / 60.0;
        double kilograms = this.weight/2.2;
        this.caloriesBurned = (int) (met * kilograms * durationInHours);
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
