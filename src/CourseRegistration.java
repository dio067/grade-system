import java.io.Serializable;

/**
 * Represents a student's registration in a course with grade
 */
public class CourseRegistration implements Serializable {
    private String studentId;
    private String courseCode;
    private double grade;

    public CourseRegistration(String studentId, String courseCode, double grade) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.grade = grade;
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }
    public double getGrade() { return grade; }

    // Setter for grade
    public void setGrade(double grade) { this.grade = grade; }

    @Override
    public String toString() {
        return studentId + "," + courseCode + "," + grade;
    }
}