
class Main {
    public static void main(String[] args) {
        System.out.println("Still Nothing");
    }
}
class Student {
    private String studentName;
    private int studentId;
    private String[] listStudent;

    //Getters
    public String getName(){ return "nothing";};
    public String getId(){ return "nothing";};
    public String[] getListStudent(){ return listStudent;}

    //Setters
    public void setId(){};
    public void setName(){};
    public void setListStudent(){};

    //methods
    public void addStudent(){};

}
class Course {
    private String courseName;
    private String[] listCourses;
    
    //Getters
    public String getCourseName(){return "nothing";};
    public String[] getListCourses(){ return listCourses;};
    
    //Setters
    public void setCourseName(){};
    public void setListCourses(){};

    //methods
    public void addCourse(){};
    public void dropCourse(){};

    
}