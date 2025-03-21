package models;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList; 
import java.util.List;
import database.signup.*;
public class Student extends User {
    private int id;
    private List<Course> courses;
    private List<StudentAttendance> attendance;
    private static int totalStudents = 1;
    private int group;
    private Department department;
    private int term;
    private double Schoolfee;
    private String Major;
    private int Gen;

    // Constructor for registration
    public Student(User s,String major,int gen) {
        super(s.name, s.email, s.phoneNumber, s.password, s.dob, s.gender,s.role);
        this.id = totalStudents;
        totalStudents++;
        courses = new ArrayList<Course>();
        attendance = new ArrayList<StudentAttendance>();
        Major = major;
        Gen = gen;
        allUsers.add(this);
    }
    //for asign old user
    public Student(User s,String major,int gen,int id,int group,int term,double SchoolFee){
        super(s.name, s.email, s.phoneNumber, s.password, s.dob, s.gender,s.role);
        this.Major= major;
        this.Gen = gen;
        this.id = id;
        this.group=group;
        this.term=term;
        this.Schoolfee = SchoolFee;
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
    public Department getDepartment(){return department;}
    public int getTerm(){return term;}
    public double getSchoolfee(){return Schoolfee;}
    public String getMajor(){return Major;}
    public int getGen(){return Gen;}
    public void addAttendance(StudentAttendance attendance) {
        this.attendance.add(attendance);
    }

    // Setters (Allow modifying values)
    public void setCourse(List<Course> courses) { this.courses = courses; } 
    public void setAttendance(List<StudentAttendance> attendance) { this.attendance = attendance; }
    public void setGroup(int group){this.group=group;}
    public void setDepartment(Department Department){this.department=Department;}
    public void setTerm(int term){this.term=term;}
    public void setSchoolfee(double Schoolfee){this.Schoolfee=Schoolfee;}
    public void setMajor(String Major){this.Major=Major;}
    public void setGen(int Gen){this.Gen=Gen;}

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
    public void signUp(Student student){
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.insertStudent(student,student.getRole());
    }
}
