import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

/**
 * Main class for the Grade System application
 */
public class GradeSystem {
    // File names
    private static final String STUDENTS_FILE = "students.txt";
    private static final String COURSES_FILE = "courses.txt";
    private static final String REGISTRATIONS_FILE = "registrations.txt";
    private static final int MAX_COURSES_PER_STUDENT = 4;
    
    private List<Student> students;
    private List<Course> courses;
    private List<CourseRegistration> registrations;
    private Scanner scanner;
    
    public GradeSystem() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        registrations = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        GradeSystem system = new GradeSystem();
        system.loadData();
        system.run();
    }
    
    // Data loading methods
    private void loadData() {
        loadStudents();
        loadCourses();
        loadRegistrations();
    }
    
    private void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    students.add(new Student(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }
    
    private void loadCourses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(COURSES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    courses.add(new Course(
                        parts[0].trim(), 
                        parts[1].trim(), 
                        Integer.parseInt(parts[2].trim())
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }
    
    private void loadRegistrations() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REGISTRATIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    registrations.add(new CourseRegistration(
                        parts[0].trim(), 
                        parts[1].trim(), 
                        Double.parseDouble(parts[2].trim())
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading registrations: " + e.getMessage());
        }
    }
    
    // Data saving methods
    private void saveStudents() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student student : students) {
                writer.println(student.getId() + "," + student.getName());
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
    
    private void saveCourses() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COURSES_FILE))) {
            for (Course course : courses) {
                writer.println(course.getCode() + "," + course.getTitle() + "," + course.getCreditHours());
            }
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }
    
    private void saveRegistrations() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(REGISTRATIONS_FILE))) {
            for (CourseRegistration reg : registrations) {
                writer.println(reg.getStudentId() + "," + reg.getCourseCode() + "," + reg.getGrade());
            }
        } catch (IOException e) {
            System.out.println("Error saving registrations: " + e.getMessage());
        }
    }
    
    // Menu system
    private void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter choice: ");
            
            switch (choice) {
                case 1: addStudent(); break;
                case 2: addCourse(); break;
                case 3: registerCourse(); break;
                case 4: dropCourse(); break;
                case 5: assignGrade(); break;
                case 6: listStudents(); break;
                case 7: listCourses(); break;
                case 8: showStudentCourses(); break;
                case 9: generateTranscript(); break;
                case 10: running = false; break;
                default: System.out.println("Invalid choice. Try again.");
            }
            
            if (running) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
                scanner.nextLine(); // Double call to handle potential newline issues
            }
        }
        
        System.out.println("Exiting system. Goodbye!");
    }
    
    private void displayMenu() {
        System.out.println("\n==== Grade System Menu ====");
        System.out.println("1. Add Student");
        System.out.println("2. Add Course");
        System.out.println("3. Register Course");
        System.out.println("4. Drop Course");
        System.out.println("5. Assign Grade");
        System.out.println("6. List Students");
        System.out.println("7. List Courses");
        System.out.println("8. Show Student Courses and Grades");
        System.out.println("9. Generate Transcript");
        System.out.println("10. Exit");
    }
    
    // Implementation of all menu options
    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        String id = getStringInput("Enter student ID: ");
        String name = getStringInput("Enter student name: ");
        
        if (getStudentById(id) != null) {
            System.out.println("Student with this ID already exists.");
            return;
        }
        
        students.add(new Student(id, name));
        saveStudents();
        System.out.println("Student added successfully.");
    }
    
    private void addCourse() {
        System.out.println("\n--- Add New Course ---");
        String code = getStringInput("Enter course code: ");
        String title = getStringInput("Enter course title: ");
        int creditHours = getIntInput("Enter credit hours: ");
        
        if (getCourseByCode(code) != null) {
            System.out.println("Course with this code already exists.");
            return;
        }
        
        courses.add(new Course(code, title, creditHours));
        saveCourses();
        System.out.println("Course added successfully.");
    }
    
    private void registerCourse() {
        System.out.println("\n--- Register Student in Course ---");
        String studentId = getStringInput("Enter student ID: ");
        String courseCode = getStringInput("Enter course code: ");
        
        Student student = getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        
        Course course = getCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        
        if (getRegistration(studentId, courseCode) != null) {
            System.out.println("Student is already registered in this course.");
            return;
        }
        
        if (getStudentRegistrations(studentId).size() >= MAX_COURSES_PER_STUDENT) {
            System.out.println("Maximum course limit reached (4 courses).");
            return;
        }
        
        registrations.add(new CourseRegistration(studentId, courseCode, 0.0));
        saveRegistrations();
        System.out.println("Course registration successful.");
    }
    
    private void dropCourse() {
        System.out.println("\n--- Drop Course for Student ---");
        String studentId = getStringInput("Enter student ID: ");
        String courseCode = getStringInput("Enter course code: ");
        
        CourseRegistration reg = getRegistration(studentId, courseCode);
        if (reg == null) {
            System.out.println("Registration not found.");
            return;
        }
        
        registrations.remove(reg);
        saveRegistrations();
        System.out.println("Course dropped successfully.");
    }
    
    private void assignGrade() {
        System.out.println("\n--- Assign Grade to Student ---");
        String studentId = getStringInput("Enter student ID: ");
        String courseCode = getStringInput("Enter course code: ");
        double grade = getDoubleInput("Enter grade: ");
        
        CourseRegistration reg = getRegistration(studentId, courseCode);
        if (reg == null) {
            System.out.println("Registration not found.");
            return;
        }
        
        reg.setGrade(grade);
        saveRegistrations();
        System.out.println("Grade assigned successfully.");
    }
    
    private void listStudents() {
        System.out.println("\n--- List of Students ---");
        for (Student student : students) {
            System.out.println(student);
        }
    }
    
    private void listCourses() {
        System.out.println("\n--- List of Courses ---");
        for (Course course : courses) {
            System.out.println(course);
        }
    }
    
    private void showStudentCourses() {
        System.out.println("\n--- Student Courses and Grades ---");
        String studentId = getStringInput("Enter student ID: ");
        
        Student student = getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        
        List<CourseRegistration> studentRegs = getStudentRegistrations(studentId);
        if (studentRegs.isEmpty()) {
            System.out.println("No courses registered for this student.");
            return;
        }
        
        System.out.println("\nCourses for " + student.getName() + ":");
        for (CourseRegistration reg : studentRegs) {
            Course course = getCourseByCode(reg.getCourseCode());
            System.out.printf("%s - Grade: %.1f%n", 
                course.getTitle(), 
                reg.getGrade());
        }
    }
    
    private void generateTranscript() {
        System.out.println("\n--- Generate Student Transcript ---");
        String studentId = getStringInput("Enter student ID: ");
        
        Student student = getStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        
        List<CourseRegistration> studentRegs = getStudentRegistrations(studentId);
        if (studentRegs.isEmpty()) {
            System.out.println("No courses registered for this student.");
            return;
        }
        
        System.out.println("\nTranscript for: " + student.getName() + " (" + studentId + ")");
        System.out.println("----------------------------------------");
        
        double totalGradePoints = 0;
        int totalCreditHours = 0;
        DecimalFormat df = new DecimalFormat("0.00");
        
        for (CourseRegistration reg : studentRegs) {
            Course course = getCourseByCode(reg.getCourseCode());
            if (course != null && reg.getGrade() > 0) {
                double gradePoint = convertGradeToPoints(reg.getGrade());
                totalGradePoints += gradePoint * course.getCreditHours();
                totalCreditHours += course.getCreditHours();
                
                System.out.printf("%s - Grade: %.1f (%d CH) - Grade Points: %s%n",
                    course.getTitle(),
                    reg.getGrade(),
                    course.getCreditHours(),
                    df.format(gradePoint));
            }
        }
        
        if (totalCreditHours > 0) {
            double gpa = totalGradePoints / totalCreditHours;
            System.out.println("----------------------------------------");
            System.out.printf("GPA: %s%n", df.format(gpa));
        } else {
            System.out.println("No graded courses found.");
        }
    }
    
    // Helper methods
    private double convertGradeToPoints(double grade) {
        if (grade >= 90) return 4.0;
        if (grade >= 80) return 3.0;
        if (grade >= 70) return 2.0;
        if (grade >= 60) return 1.0;
        return 0.0;
    }
    
    private Student getStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }
    
    private Course getCourseByCode(String code) {
        for (Course c : courses) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
    
    private CourseRegistration getRegistration(String studentId, String courseCode) {
        for (CourseRegistration reg : registrations) {
            if (reg.getStudentId().equals(studentId) && reg.getCourseCode().equals(courseCode)) {
                return reg;
            }
        }
        return null;
    }
    
    private List<CourseRegistration> getStudentRegistrations(String studentId) {
        List<CourseRegistration> result = new ArrayList<>();
        for (CourseRegistration reg : registrations) {
            if (reg.getStudentId().equals(studentId)) {
                result.add(reg);
            }
        }
        return result;
    }
    
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}