package com.example.calorie_calendar.calendar;

public class User {
    private final String name;
    private double weight;
    private int height;
    private double bmr;
    private int age;
    private WeeklyTotal week;
    private final Gender gender;
    enum Gender {MALE, FEMALE};

    public User(String name, double weight, Gender gender, int height, int age) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.height = height;
        this.age = age;
        this.week = new WeeklyTotal(bmr, weight);
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public WeeklyTotal getWeek() {
        return week;
    }

    public double getBmr() {
        return bmr;
    }
   // For men: ((9.65 × (weight in lb/2.2)) + (573 × (height in inches * .0254)) – (5.08 × age in years) + 260) * 1.2
   //  For women: ((7.38 × (weight in lb/2.2)) + (607 × (height in inches * .0254)) – (2.31 × age in years) + 43) * 1.2
    public void calculateBMR(){
        if(gender == Gender.MALE){
            this.bmr = ((9.65 * (weight/2.2)) + (573 * (height * .0254)) - (5.08 * age) + 260) * 1.2;
        } else if(gender == Gender.FEMALE){
            this.bmr = ((7.38 * (weight/2.2)) + (607 * (height * .0254)) - (2.31 * age) + 43) * 1.2;
        }
    }
}
