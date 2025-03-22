package models;
import java.time.LocalDate;
import java.time.LocalTime;
public class StudentAttendance extends Attendance {
    private int courseId; // Only for students

    // Constructor
    public StudentAttendance( int personId, String status, String remarks, int courseId) {
        LocalDate date = LocalDate.now(); // or any specific date
        LocalTime time = LocalTime.now(); // or any specific time
        super( date, time, personId, status, remarks);
        this.courseId = courseId;
    }
    //constructor without remarks
    public StudentAttendance( int personId, String status, int courseId) {
        LocalDate date = LocalDate.now(); // or any specific date
        LocalTime time = LocalTime.now(); // or any specific time
        super( date, time, personId, status);
        this.courseId = courseId;
        
    }
    //asign value
    public StudentAttendance( int personId, String status, String remarks, int courseId, java.sql.Date date, java.sql.Time time) {
        super(date.toLocalDate(), time.toLocalTime(), personId, status, remarks);
        this.courseId = courseId;
    }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    @Override
    public String toString() {
        return "Date : " + date + ", Time : " + time + ", Status : " + status + ", Remarks : " + remarks ;
    }
    // @Override
    // public boolean equals(Object obj) {
    //     if (obj instanceof StudentAttendance) {
    //         StudentAttendance other = (StudentAttendance) obj;
    //         return date.equals(other.date) && time.equals(other.time) && personId == other.personId && status.equals(other.status) && remarks.equals(other.remarks) && courseId.equals(other.courseId);
    //     }
    //     return false;
    // }
}

