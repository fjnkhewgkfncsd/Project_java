package models;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList; 
import java.util.List;
public class Student extends User {
    private int id;
    private List<Course> courses;
    private List<StudentAttendance> attendance;
    private static int totalStudents = 1;
    private int group;
    private String Department;
    private int term;
    private double Schoolfee;


    // Constructor for registration
    public Student(User s) {
        super(s.name, s.email, s.phoneNumber, s.password, s.dob, s.gender,s.address);
        this.id = totalStudents;
        totalStudents++;
        courses = new ArrayList<Course>();
        attendance = new ArrayList<StudentAttendance>();
        allUsers.add(this);
    }

    public void submitAttendance(String courseId, String status, String remarks) {
        try {
            StudentAttendance newRecord = new StudentAttendance(getId(), courseId, status, remarks);
            attendance.add(newRecord);
            System.out.println("✅ Attendance recorded for Student: " + name + " in Course: " + courseId);
        } catch (Exception e) {
            System.out.println("❌ Failed to submit attendance: " + e.getMessage());
        }
    }

    public void checkAttendance() {
        try {
            System.out.println("📌 Attendance Records for Student " + name + " (ID: " + id + "):");
            for (StudentAttendance record : attendance) {
                System.out.println(record.getDate() + " - " + record.getStatus() + " - Course: " + record.getCourseId());
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to check attendance: " + e.getMessage());
        }
    }

    // Getters (Allow reading values)
    public int getId() { return id; }
    public List<Course> getCourses() { return courses; }    
    public List<StudentAttendance> getAttendance() { return attendance; }
    public int getgroup(){return group;}
    public String getDepartment(){return Department;}
    public int getTerm(){return term;}
    public double getSchoolfee(){return Schoolfee;}

    // Setters (Allow modifying values)
    public void setCourse(List<Course> courses) { this.courses = courses; } 
    public void setAttendance(List<StudentAttendance> attendance) { this.attendance = attendance; }
    public void setGroup(int group){this.group=group;}
    public void setDepartment(String Department){this.Department=Department;}
    public void setTerm(int term){this.term=term;}
    public void setSchoolfee(double Schoolfee){this.Schoolfee=Schoolfee;}
    
    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "id=" + id +
                ", courses=" + courses +
                ", attendance=" + attendance +
                '}';
    }
    @Override
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
