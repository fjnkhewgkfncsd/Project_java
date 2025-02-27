import java.time.LocalDate;
import java.time.LocalTime;
public class StudentAttendance extends Attendance {
    private String courseId; // Only for students

    // Constructor
    public StudentAttendance( int personId, String status, String remarks, String courseId) {
        LocalDate date = LocalDate.now(); // or any specific date
        LocalTime time = LocalTime.now(); // or any specific time
        super( date, time, personId, status, remarks);
        this.courseId = courseId;
    }
    //constructor without remarks
    public StudentAttendance( int personId, String status, String courseId) {
        LocalDate date = LocalDate.now(); // or any specific date
        LocalTime time = LocalTime.now(); // or any specific time
        super( date, time, personId, status);
        this.courseId = courseId;
    }
    
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public String toString() {
        return "StudentAttendance{" +
                "date=" + date +
                ", time=" + time +
                ", personId=" + personId +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
    public boolean equals(Object obj) {
        if (obj instanceof StudentAttendance) {
            StudentAttendance other = (StudentAttendance) obj;
            return date.equals(other.date) && time.equals(other.time) && personId == other.personId && status.equals(other.status) && remarks.equals(other.remarks) && courseId.equals(other.courseId);
        }
        return false;
    }
}

