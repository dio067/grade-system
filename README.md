# Grade Management System

## Overview

This Java console application is a Small Grade System that manages student records, course information, and grade assignments. The system stores all data in text files for persistence between sessions, following the exact specifications provided in the project requirements.

## Features

- **Student Management**: Add and view students
- **Course Management**: Add and view courses
- **Course Registration**: Register students for courses (max 4 per student)
- **Grade Assignment**: Assign grades to registered students
- **Transcript Generation**: Calculate and display student GPA
- **Data Persistence**: All data saved to text files

## File Structure

The system uses three text files with specific formats:

1. `students.txt` - Stores student data:

   ```
   studentId,studentName
   2025001111,Ali Ahmed
   2025002222,John Smith
   ```

2. `courses.txt` - Stores course data:

   ```
   courseCode,courseTitle,creditHours
   MATH113,Calculus 1,3
   ```

3. `registrations.txt` - Stores registration and grade data:
   ```
   studentId,courseCode,grade
   2025001111,MATH113,80.0
   2025002222,PHYS110,85.5
   ```

## Menu Options

The system provides the following menu options:

1. Add New Student
2. Add New Course
3. Register Student in a Course
4. Drop Course for a Student
5. Assign Grade to a Student
6. Display List of Students
7. Display List of Courses
8. Display Registered Courses and Grades for a Student
9. Generate Transcript for a Student
10. Exit

## How to Run

1. Compile all Java files:

   ```bash
   javac *.java
   ```

2. Run the application:

   ```bash
   java GradeSystem
   ```

3. The system will automatically create the required text files if they don't exist.

## System Requirements

- Java JDK 8 or higher
- Command line interface

## Sample Usage

1. Add a student:

   ```
   Enter choice: 1
   Enter student ID: 2025003333
   Enter student name: Sarah Johnson
   ```

2. Register a student for a course:

   ```
   Enter choice: 3
   Enter student ID: 2025003333
   Enter course code: MATH113
   ```

3. Assign a grade:

   ```
   Enter choice: 5
   Enter student ID: 2025003333
   Enter course code: MATH113
   Enter grade: 92.5
   ```

4. Generate a transcript:

   ```
   Enter choice: 9
   Enter student ID: 2025003333

   Transcript for: Sarah Johnson (2025003333)
   ----------------------------------------
   Calculus 1 - Grade: 92.5 (3 CH) - Grade Points: 4.00
   ----------------------------------------
   GPA: 4.00
   ```

## Implementation Details

- Uses object-oriented principles with Student, Course, and CourseRegistration classes
- Implements file I/O for data persistence
- Includes input validation and error handling
- Follows the exact specifications from the project requirements

## Notes

- The system creates the required text files automatically if they don't exist
- All changes are saved immediately after each operation
- The menu system matches the examples shown in the project specification
