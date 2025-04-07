package com.university;

import java.io.*;
import java.util.ArrayList;

public class UniversitySystem {
    private ArrayList<Course> listCourses = new ArrayList<>();
    private ArrayList<Student> listStudents = new ArrayList<>();
    private static int lastStudentId = 0;
    private static int lastCourseId = 0;

    // Constructor
    public UniversitySystem() {
        lastCourseId = 0;
        lastStudentId = 0;
    }

    // Getters
    public ArrayList<Student> getStudents() {
        return listStudents;
    }

    // Methods for managing courses
    public void addCourse(Course newCourse) {
        newCourse.setCourseId(lastCourseId++);
        listCourses.add(newCourse);
    }

    public void removeCourse(int removedCourseId) {
        listCourses.removeIf(course -> course.getCourseId() == removedCourseId);
    }

    public void displayCourses() {
        for (Course course : listCourses) {
            System.out.println("Course ID: " + course.getCourseId());
            System.out.println("Course Code: " + course.getCourseCode() + " " + "Course Name: " + course.getCourseTitle() + "(" + course.getCreditHour() + "hrs)");
        }
    }

    // Methods for managing students
    public void addStudent(Student newStudent) {
        newStudent.setId(++lastStudentId);
        listStudents.add(newStudent);
    }

    public void removeStudent(int removedStudentId) {
        listStudents.removeIf(student -> student.getId() == removedStudentId);
    }

    public void displayStudents() {
        for (Student student : listStudents) {
            student.displayStudentInfo();
        }
    }

    // Methods for course registration
    public void registerCourse(Student student, int courseId) {
        if (student.getRegisteredCourses().size() >= 4) {
            System.out.println("You cannot register for more than 4 courses.");
            return;
        }

        for (Course course : listCourses) {
            if (course.getCourseId() == courseId && course.isAvailable()) {
                student.getRegisteredCourses().add(course);
                course.setAvailability(false);
                System.out.println("You enrolled in the course successfully!");
                return;
            }
        }
        System.out.println("Course not available!");
    }

    public void dropCourse(Student student, int courseId) {
        for (Course course : student.getRegisteredCourses()) {
            if (course.getCourseId() == courseId && !course.isAvailable()) {
                student.getRegisteredCourses().remove(course);
                course.setAvailability(true);
                System.out.println("You dropped the course successfully!");
                return;
            }
        }
        System.out.println("Course not found in registered courses.");
    }

    public void assignGrade(Student student, int courseId, double grade) {
        for (Course course : student.getRegisteredCourses()) {
            if (course.getCourseId() == courseId && !course.isAvailable()) {
                course.setGrade(grade);
                System.out.println("Grade assigned successfully!");
                return;
            }
        }
        System.out.println("Cannot assign grade, course doesn't match any on your schedule!");
    }

    public void viewTranscript(Student student) {
        double totalCredits = 0;
        double weightedGrades = 0;

        System.out.println("Transcript for " + student.getName() + ":");
        for (Course course : student.getRegisteredCourses()) {
            System.out.println(course.getCourseTitle() + " - Grade: " + course.getGrade() + " (Credits: " + course.getCreditHour() + ")");
            totalCredits += course.getCreditHour();
            weightedGrades += course.getGrade() * course.getCreditHour();
        }

        if (totalCredits > 0) {
            double gpa = weightedGrades / totalCredits;
            System.out.println("GPA: " + gpa);
        } else {
            System.out.println("No courses registered.");
        }
    }

    // File handling methods
    public void saveToFile() {
        try (BufferedWriter studentWriter = new BufferedWriter(new FileWriter("students.txt"));
             BufferedWriter courseWriter = new BufferedWriter(new FileWriter("courses.txt"));
             BufferedWriter registrationWriter = new BufferedWriter(new FileWriter("registrations.txt"))) {

            // Writing students
            for (Student student : listStudents) {
                studentWriter.write(student.getId() + "," + student.getName());
                studentWriter.newLine();
            }

            // Writing courses
            for (Course course : listCourses) {
                courseWriter.write(course.getCourseCode() + "," + course.getCourseTitle() + "," + course.getCreditHour());
                courseWriter.newLine();
            }

            // Writing registrations
            for (Student student : listStudents) {
                for (Course course : student.getRegisteredCourses()) {
                    registrationWriter.write(student.getId() + "," + course.getCourseCode() + "," + course.getGrade());
                    registrationWriter.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        try (BufferedReader studentReader = new BufferedReader(new FileReader("students.txt"));
             BufferedReader courseReader = new BufferedReader(new FileReader("courses.txt"));
             BufferedReader registrationReader = new BufferedReader(new FileReader("registrations.txt"))) {

            String line;
            while ((line = studentReader.readLine()) != null) {
                String[] parts = line.split(",");
                Student student = new Student();
                student.setId(Integer.parseInt(parts[0]));
                student.setName(parts[1]);
                listStudents.add(student);
            }

            while ((line = courseReader.readLine()) != null) {
                String[] parts = line.split(",");
                Course course = new Course();
                course.setCourseCode(parts[0]);
                course.setCourseTitle(parts[1]);
                course.setCreditHour(Integer.parseInt(parts[2]));
                listCourses.add(course);
            }

            while ((line = registrationReader.readLine()) != null) {
                String[] parts = line.split(",");
                int studentId = Integer.parseInt(parts[0]);
                String courseCode = parts[1];
                double grade = Double.parseDouble(parts[2]);

                // Find corresponding student and course
                Student student = null;
                Course course = null;
                for (Student s : listStudents) {
                    if (s.getId() == studentId) {
                        student = s;
                        break;
                    }
                }
                for (Course c : listCourses) {
                    if (c.getCourseCode().equals(courseCode)) {
                        course = c;
                        break;
                    }
                }

                if (student != null && course != null) {
                    student.getRegisteredCourses().add(course);
                    course.setGrade(grade);
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
