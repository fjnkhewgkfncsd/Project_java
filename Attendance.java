import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Attendance {
    protected LocalDate date;
    protected LocalTime time;
    protected String personId;
    protected String status;
    protected String remarks;
    private ArrayList<String> attendanceRecords = new ArrayList<>();

    // Constructor
    public Attendance(LocalDate date, LocalTime time, String personId, String status, String remarks) {
        this.date = date;
        this.time = time;
        this.personId = personId;
        this.status = status;
        this.remarks = remarks;
    }

    // Getters and Setters

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public String getPersonId() { return personId; }
    public void setPersonId(String personId) { this.personId = personId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public void submitAttendance(LocalDate date, boolean isPresent) {
        String record = "Date: " + date + ", Present: " + (isPresent ? "Yes" : "No");
        attendanceRecords.add(record);
    }

    public void checkAttendance() {
        System.out.println("Attendance Records for staffId " + personId + ":");
        for (String record : attendanceRecords) {
            System.out.println(record);
        }
    }
}