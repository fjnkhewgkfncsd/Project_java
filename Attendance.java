import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {
    protected LocalDate date;
    protected LocalTime time;
    protected int personId;
    protected String status;
    protected String remarks;
       // Constructor
    public Attendance(LocalDate date, LocalTime time, int personId, String status, String remarks) {
        this.date = date;
        this.time = time;
        this.personId = personId;
        this.status = status;
        this.remarks = remarks;
    }
    public Attendance(LocalDate date, LocalTime time, int personId, String status) {
        this.date = date;
        this.time = time;
        this.personId = personId;
        this.status = status;
    }
    // Getters and Setters

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public int getPersonId() { return personId; }
    public void setPersonId(int personId) { this.personId = personId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}