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
    private double bmr = 0;
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

    // public AppUser(String name, double weight, Gender gender, int height, int age) {
    //     if(!name.isEmpty() && !name.isBlank()){
    //         this.name = name;
    //     } else{
    //         throw new IllegalArgumentException("Name is empty or Blank");
    //     }
    //     if(weight <= 0 || height <= 0 || age <= 0){
    //         throw new IllegalArgumentException("Weight, height, or age is not valid a positive number");
    //     }
    //     this.weight = weight;
    //     this.height = height;
    //     this.age = age;
    //     this.gender = gender;
    //     this.weeklyTotal = new WeeklyTotal(this);
    // }

    public List<Activity> getActivities(){
        return this.activities;
    }

    public Long getId(){
        return this.id;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender(){
        return this.gender;
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

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGender(Gender gender){
        this.gender = gender;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWeek(WeeklyTotal week) {
        this.weeklyTotal = week;
    }

    public double getBmr() {
        return bmr;
    }
    public void setBmr(double bmr){
        this.bmr = bmr;
    }
}
