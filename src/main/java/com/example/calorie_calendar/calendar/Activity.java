package com.example.calorie_calendar.calendar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Activity {
    private double time;
    private double length;
    private int caloriesBurned;
    private double speed;
    private double met;

    public Activity(long time, double length, double speed, double weight){
        this.time = time;
        this.length = length;
        this.speed = speed;
        setMet();
        calculateCaloriesBurned(weight);
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public double getTime() {
        return time;
    }

    public double getLength() {
        return length;
    }

    public double getSpeed() {
        return speed;
    }

    public void setMet(){
        String fileName = "met.csv";
        File file = new File(fileName);
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null && this.met == 0){
                String[] split = line.split(",");
                double tempo = Double.parseDouble(split[0]);
                if(this.speed >= tempo && this.speed < tempo + 1){
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
        this.caloriesBurned = (int) (met * weight * time);
    }
}
