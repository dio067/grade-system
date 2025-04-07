package com.university.utilities;

import java.util.ArrayList;

public class Student {
    private int id;
    private String name;
    private ArrayList<Course> registeredCourses;
    
    public Student() {
        this.registeredCourses = new ArrayList<>();
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    // Method to display student info
    public void displayStudentInfo() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Registered Courses: ");
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println(course.getCourseTitle());
            }
        }
    }
}
