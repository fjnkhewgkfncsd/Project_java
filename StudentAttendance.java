import java.time.LocalDate;
import java.time.LocalTime;
public class StudentAttendance extends Attendance {
    private String courseId; // Only for students

    // Constructor
    public StudentAttendance( LocalDate date, LocalTime time, String personId, String status, String remarks, String courseId) {
        super( date, time, personId, status, remarks);
        this.courseId = courseId;
    }
    
    public void submitAttendance(LocalDate date,LocalTime time, boolean isPresent,String courseId) {
        String record = "Date: " + date + ", Present: " + (isPresent ? "Yes" : "No") + ", CourseId: " + courseId;
        attendanceRecords.add(record);
        System.out.println("Attendance submitted successfully");
        System.out.println(record);
    }
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    @Override
    public void checkAttendance() {
        System.out.println("Attendance Records for studentId " + personId + ":");
        for (String record : attendanceRecords) {
            System.out.println(record);
        }
    }
}

