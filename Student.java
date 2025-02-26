import java.util.ArrayList; 
import java.util.List;
public class Student extends User {
    private int id;
    private List<Course> courses;
    private List<StudentAttendance> attendance;
    private static int totalStudents = 0;

    // Constructor for registration
    public Student(User s) {
        super(s.name, s.email, s.phoneNumber, s.password, s.dob, s.gender,s.address);
        this.id = totalStudents;
        totalStudents++;
        courses = new ArrayList<Course>();
        attendance = new ArrayList<StudentAttendance>();
    }

    @Override
    public boolean login(String username,String password){
        if(this.password==password){
            return true;
        }
        return false;
    }
    public boolean signup(String name,String password,String phonenumber,char sex,String dob,String email){
        return true;
    }

    public void submitAttendent(String username,String password){
        return;
    }
    public void checkAttendent(String username,String password){
        return;
    }

    // Getters (Allow reading values)
    public int getId() { return id; }
    public List<Course> getCourses() { return courses; }    
    public List<StudentAttendance> getAttendance() { return attendance; }

    // Setters (Allow modifying values)
    public void setCourse(List<Course> courses) { this.courses = courses; } 
    public void setAttendance(List<StudentAttendance> attendance) { this.attendance = attendance; }

    public String toString() {
        return super.toString() + "Student{" +
                "id=" + id +
                ", courses=" + courses +
                ", attendance=" + attendance +
                '}';
    }
    public boolean equals(Object obj){
        if(obj==this){
            return true;
        }
        if(obj==null || obj.getClass()!=this.getClass()){
            return false;
        }
        Student s = (Student) obj;
        return s.id==this.id;
    }

}
