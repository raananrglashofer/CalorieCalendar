package com.example.calorie_calendar.calendar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;

public class Activity {
    private Duration duration; // in hours
    private double length;
    private int caloriesBurned;
    private double met;

    public Activity(Duration time, double length, double weight){
        if(time.toMinutes() <= 0 || length <+ 0 || weight <= 0){
            throw new IllegalArgumentException("Time, Length, Speed, or Weight are not a positive number");
        }
        this.duration = time;
        this.length = length;
        setMet();
        calculateCaloriesBurned(weight);
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public Duration getTime() {
        return duration;
    }

    public double getLength() {
        return length;
    }

    public void setMet(){
        double speed = length / duration.toHours(); // this math could be wrong
        String fileName = "met.csv";
        File file = new File(fileName);
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null && this.met == 0){
                String[] split = line.split(",");
                double tempo = Double.parseDouble(split[0]);
                if(speed >= tempo && speed < tempo + 1){
                    double curMet = Double.parseDouble(split[1]);
                    this.met = curMet;
                }
            }
        } catch(Exception e){
            throw new RuntimeException();
        }
    }

    // Calories Burned = MET x Body Weight (kg) x Duration of Running (hours)
    public void calculateCaloriesBurned(double weight){
        double durationInHours = duration.toMinutes() / 60.0;
        this.caloriesBurned = (int) (met * weight * durationInHours);
    }
}
