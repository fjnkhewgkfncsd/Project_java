import java.time.LocalDate;
import java.time.LocalTime;
public class StudentAttendance extends Attendance {
    private String courseId; // Only for students

    // Constructor
    public StudentAttendance( LocalDate date, LocalTime time, String personId, String status, String remarks, String courseId) {
        super( date, time, personId, status, remarks);
        this.courseId = courseId;
    }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
}

