import java.io.Serializable;

/**
 * Represents a course with code, title, and credit hours
 */
public class Course implements Serializable {
    private String code;
    private String title;
    private int creditHours;

    public Course(String code, String title, int creditHours) {
        this.code = code;
        this.title = title;
        this.creditHours = creditHours;
    }

    // Getters
    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCreditHours() { return creditHours; }

    @Override
    public String toString() {
        return code + ": " + title + " (" + creditHours + " CH)";
    }
}
