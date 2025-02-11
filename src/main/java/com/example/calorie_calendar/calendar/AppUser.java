package com.example.calorie_calendar.calendar;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double weight;
    private int height;
    @Column(nullable = true)
    private int bmr;
    private int age;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activity> activities = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private WeeklyTotal weeklyTotal;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    public enum Gender {MALE, FEMALE};
    public AppUser() {
        // for database
    }

    public AppUser(String name, double weight, Gender gender, int height, int age) {
        if(!name.isEmpty() && !name.isBlank()){
            this.name = name;
        } else{
            throw new IllegalArgumentException("Name is empty or Blank");
        }
        if(weight <= 0 || height <= 0 || age <= 0){
            throw new IllegalArgumentException("Weight, height, or age is not valid a positive number");
        }
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
        calculateBMR();
        this.weeklyTotal = new WeeklyTotal(this);
    }

    public List<Activity> getActivities(){
        return this.activities;
    }

    public void addActivity(Activity activity, Day day){
        this.activities.add(activity);
        this.weeklyTotal.addActivityToDay(activity, day);
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public WeeklyTotal getWeek() {
        return weeklyTotal;
    }

    public int getBmr() {
        return bmr;
    }
   // For men: ((9.65 × (weight in lb/2.2)) + (573 × (height in inches * .0254)) – (5.08 × age in years) + 260) * 1.2
   //  For women: ((7.38 × (weight in lb/2.2)) + (607 × (height in inches * .0254)) – (2.31 × age in years) + 43) * 1.2
    public void calculateBMR(){
        if(gender == Gender.MALE){
            this.bmr = (int) (((9.65 * (weight/2.2)) + (573 * (height * .0254)) - (5.08 * age) + 260) * 1.2);
        } else if(gender == Gender.FEMALE){
            this.bmr = (int) (((7.38 * (weight/2.2)) + (607 * (height * .0254)) - (2.31 * age) + 43) * 1.2);
        }
    }
}
