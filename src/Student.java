import java.io.Serializable;

/**
 * Represents a student with ID and name
 * Implements Serializable for potential future file storage enhancements
 */
public class Student implements Serializable {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}