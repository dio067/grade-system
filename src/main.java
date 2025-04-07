import java.util.ArrayList;


class Main {
    public static void main(String[] args) {
        System.out.println("Still Nothing");
    }
}
class Student {
    private String studentName;
    private int studentId;
    private static String[] registeredCourses;

    //Getters
    public String getName(){ return "nothing";};
    public String getId(){ return "nothing";};
    public String[] getRegisteredCourses(){ return registeredCourses;};

    //Setters
    public void setId(){};
    public void setName(){};

    //methods
    public void studentInfromation(){
        System.out.println("Student Name: " + studentName + "\nStudent Id: " + studentId);
    }

}
class Course {
    private String courseName;
    private int criedtHour, courseId;
    private boolean isAvailable;

    //counstructor
    public Course(){
        isAvailable = true;
    }
    
    //Getters
    public String getCourseName(){return "nothing";};
    public int getCridetHour(){ return 0;};
    public int getCourseId() {return 0;};
    public boolean getAvailability(){
        return isAvailable;
    };

    //Setters
    public void setCourseName(){};
    public void setCridetHour(){}; 
    public void setCourseId(int courseId){};
    public void setAvailability(boolean condition){
         isAvailable = condition;
    };
}


class System {

    private ArrayList<Object> listCourses = new ArrayList<>();
    private ArrayList<Object> listStudent = new ArrayList<>();
    private static int lastStudentId;
    private static int lastCourseId;

     //constructors
    public System(){
        lastCourseId = 0;
        lastStudentId = 0;
     };
     //Setters
    public ArrayList<Object> getStudents(){
        return listStudent;
     };

     //methods
    public void addCourse(Course newCourse){
       
        newCourse.setCourseId(lastCourseId++);
        listCourses.add(newCourse);
     };

    public void removeCourse(int removedCourseId){

        listCourses.removeIf(course -> ((Course) course).getCourseId() == removedCourseId);
    };

    public void displayCourses(){

        for(Course course : listCourses){

            System.out.println(course.getCourseId());
            System.out.println(course.getCourseName());

        }
    };

    public void addStudent(Student newStudent){

        newStudent.studentId(++lastStudentId);
        listStudent.add(newStudent);
     };

    public void removeStudent(int removedStudentId){
        listStudent.removeIf(student -> ((Student) student).getCourseId() == removedStudentId);
     };
    
    public void displayStudents(){
        for(Student student : listStudent){
            student.studentInfromation;
        }
     };

    public void registerCourse(Student student, int courseId) {
        for(Course course : listCourses){
            if(course.getCourseId() == courseId && course.getAvailability() == true;){
                student.getRegisteredCourses.add(course);
                course.getAvailability == false;
                System.out.println("YOU  ENROLLED THE COURSE SUCCSESFULLY!!");
                return;
            }
        }
        
        System.out.println("NOT AVAILABLE!!");
     };

    public void dropCourse(Student student, int courseId){
        for(Course course : listCourses){
            if(course.getCourseId() == courseId && course.getAvailability() == false;){
                student.getRegisteredCourses.remove(course);
                course.getAvailability == true;
                System.out.println("YOU DROPPED THE COURSE SUCCSESFULLY!!");
                return;
            }
        }

    }
}