package models;
import java.util.ArrayList;
import java.util.List;

import database.signup.StaffDAO;

import java.time.LocalDate;
import java.time.LocalTime;
public class Staff extends User {
    protected int staffId;
    String position;
    String hireDate;
    String endDate;
    private double salary;
    private List<Attendance> attendanceList;
    static int totalStaffCount = 0;
    static ArrayList<Staff> staffList = new ArrayList<>();

    public Staff(User s, String position) {
        super(s.name, s.email, s.phoneNumber, s.password, s.dob, s.gender,s.role);
        this.staffId = totalStaffCount++;
        this.position = position;
        staffList.add(this);
        attendanceList = new ArrayList<>();
    }
    
    //getters
    public int getId() { 
        return staffId;
    }
    public String getPosition() {
        return position;
    }
    public String getHireDate() {
        return hireDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public double getSalary() {
        return salary;
    }
    //setters
    public void setPosition(String position) {
        this.position = position;
    }
    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public static ArrayList<Staff> getStaffList() {
        return staffList;
    }
    public List<Attendance> getattendanceList() {
        return attendanceList;
    }
    @Override
    public String toString() {
        return super.toString() + "Staff{" +
                "staffId=" + staffId +
                ", position='" + position + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", salary=" + salary +
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
        Staff staff = (Staff) obj;
        return staffId == staff.staffId && position.equals(staff.position) && hireDate.equals(staff.hireDate) && endDate.equals(staff.endDate) && salary == staff.salary;
    }

    public void checkAttendance() {
        try {
            System.out.println("📌 Attendance Records for Staff " + name + " (ID: " + staffId + "):");
            for (Attendance record : attendanceList) {
                System.out.println(record.getDate() + " - " + record.getStatus());
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to check attendance: " + e.getMessage());
        }
    }

    // Method to add attendance for a staff member
    public void addAttendance(Attendance attendanceRecord) {
        attendanceList.add(attendanceRecord);
        System.out.println("Attendance added for staff ID: " + attendanceRecord.getPersonId());
    }

    // Method to update attendance for a staff member
    public void updateAttendance(int personId, String date, boolean present) {
        for (Attendance record : attendanceList) {
            if (record.getPersonId() == personId && record.getDate().toString().equals(date)) {
                record.setStatus(present ? "Present" : "Absent");
                System.out.println("Attendance updated for staff ID: " + personId);
                return;
            }
        }
        System.out.println("Attendance record not found for staff ID: " + personId);
    }

    // Method to get attendance for a staff member
    public ArrayList<Attendance> getAttendance(int personId) {
        ArrayList<Attendance> staffAttendance = new ArrayList<>();
        for (Attendance record : attendanceList) {
            if (record.getPersonId() == personId) {
                staffAttendance.add(record);
            }
        }
        return staffAttendance;
    }

    public void signUp(Staff staff){
        StaffDAO staffDAO = new StaffDAO();
        staffDAO.insertStaff(staff,staff.getRole());
    }
}
