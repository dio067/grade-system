import java.util.ArrayList;

class Main {
    public static void main(String[] args) {
        System.out.println("Still Nothing");
    }
}

class Student {
    private String studentName;
    private int studentId;
    private static ArrayList<Course> registeredCourses = new ArrayList<>();

    // Getters
    public String getName() { return studentName; }
    public int getId() { return studentId; }
    public ArrayList<Course> getRegisteredCourses() { return registeredCourses; }

    // Setters
    public void setId(int studentId) { this.studentId = studentId; }
    public void setName(String studentName) { this.studentName = studentName; }

    // Methods
    public void studentInformation() {
        System.out.println("Student Name: " + studentName + "\nStudent Id: " + studentId);
    }
}

class Course {
    private String courseName;
    private int creditHour, courseId;
    private boolean isAvailable;

    // Constructor
    public Course() {
        isAvailable = true;
    }

    // Getters
    public String getCourseName() { return courseName; }
    public int getCreditHour() { return creditHour; }
    public int getCourseId() { return courseId; }
    public boolean getAvailability() { return isAvailable; }

    // Setters
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCreditHour(int creditHour) { this.creditHour = creditHour; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setAvailability(boolean condition) { isAvailable = condition; }
}

class System {
    private ArrayList<Course> listCourses = new ArrayList<>();
    private ArrayList<Student> listStudent = new ArrayList<>();
    private static int lastStudentId = 0;
    private static int lastCourseId = 0;

    // Constructor
    public System() {
        lastCourseId = 0;
        lastStudentId = 0;
    }

    // Setters
    public ArrayList<Student> getStudents() {
        return listStudent;
    }

    // Methods
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
            System.out.println("Course Name: " + course.getCourseName());
        }
    }

    public void addStudent(Student newStudent) {
        newStudent.setId(++lastStudentId);
        listStudent.add(newStudent);
    }

    public void removeStudent(int removedStudentId) {
        listStudent.removeIf(student -> student.getId() == removedStudentId);
    }

    public void displayStudents() {
        for (Student student : listStudent) {
            student.studentInformation();
        }
    }

    public void registerCourse(Student student, int courseId) {
        for (Course course : listCourses) {
            if (course.getCourseId() == courseId && course.getAvailability()) {
                student.getRegisteredCourses().add(course);
                course.setAvailability(false);
                System.out.println("YOU ENROLLED IN THE COURSE SUCCESSFULLY!!");
                return;
            }
        }
        System.out.println("COURSE NOT AVAILABLE!!");
    }

    public void dropCourse(Student student, int courseId) {
        for (Course course : listCourses) {
            if (course.getCourseId() == courseId && !course.getAvailability()) {
                student.getRegisteredCourses().remove(course);
                course.setAvailability(true);
                System.out.println("YOU DROPPED THE COURSE SUCCESSFULLY!!");
                return;
            }
        }
    }
}
