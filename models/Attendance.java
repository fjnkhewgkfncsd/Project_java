package models;
import java.time.LocalDate;
import java.time.LocalTime;
import models.*;

public class Attendance {
    protected LocalDate date;
    protected LocalTime time;
    protected int personId;
    protected String status; // "Present" or "Absent"
    protected String remarks;
    protected boolean isPresent; // Tracks attendance as boolean value

    // Constructor with remarks
    public Attendance(LocalDate date, LocalTime time, int personId, String status, String remarks) {
        this.date = date;
        this.time = time;
        this.personId = personId;
        setStatus(status); // Ensure valid status
        this.remarks = remarks != null ? remarks : "No remarks"; // Default remarks if null
        this.isPresent = status.equalsIgnoreCase("Present");
    }

    // Constructor without remarks
    public Attendance(LocalDate date, LocalTime time, int personId, String status) {
        this(date, time, personId, status, "No remarks"); // Default remarks
    }

    // Getters and Setters
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public int getPersonId() { return personId; }
    public void setPersonId(int personId) { this.personId = personId; }

    public String getStatus() { return status; }
    public void setStatus(String status) {
        if ("Present".equalsIgnoreCase(status) || "Absent".equalsIgnoreCase(status)) {
            this.status = status;
            this.isPresent = "Present".equalsIgnoreCase(status); // Update attendance status
        } else {
            throw new IllegalArgumentException("Status must be 'Present' or 'Absent'");
        }
    }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks != null ? remarks : "No remarks"; }

    // New method to directly get if the student is present
    public boolean isPresent() { return isPresent; }

    // New method to set attendance as present or absent directly
    public void setPresent(boolean isPresent) {
        this.isPresent = isPresent;
        this.status = isPresent ? "Present" : "Absent"; // Update status accordingly
    }

    // toString Method
    @Override
    public String toString() {
        return "Attendance{" +
                "date=" + date +
                ", time=" + time +
                ", personId=" + personId +
                ", status='" + status + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
