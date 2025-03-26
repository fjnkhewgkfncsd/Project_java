package models;
import java.util.ArrayList;
import java.util.List;

import database.signup.StaffDAO;

import java.time.LocalDate;
import java.time.LocalTime;
public class Staff extends User {
    private int id;
    String position;
    String hireDate;
    String endDate;
    private double salary;
    static int totalStaff = 0;
    private ArrayList<Attendance> attendance;  
    private static ArrayList<Staff> staffList = new ArrayList<Staff>();

    public Staff(User s, String position) {
        super(s.name, s.email, s.password, s.password, s.dob, s.gender,s.role);
        this.id = totalStaff++;
        this.position = position;
        this.salary = salary; // Fix: Initialize salary
        staffList.add(this);
        attendance = new ArrayList<Attendance>();
    }

    // New constructor to match FetchData's fetchStaff method
    public Staff(User user, String position, int id) {
        super(user.name, user.email, user.phoneNumber, user.password, user.dob, user.gender, user.role);
        this.position = position;
        this.id = id;
        this.salary = 0.0; // Default salary
        attendance = new ArrayList<>();
    }
    
    public static int getTotalStaff() {
        return totalStaff;
    }
    //getters
    public int getId() { 
        return id;
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
    public ArrayList<Attendance> getAttendanceList() {
        return attendance;
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

    // Static methods to manage staffList
    public static void addStaff(Staff staff) {
        staffList.add(staff);
    }

    public static void removeStaffById(int id) {
        staffList.removeIf(staff -> staff.getId() == id);
    }

    public static Staff getStaffById(int id) {
        for (Staff staff : staffList) {
            if (staff.getId() == id) {
                return staff;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + "Staff{" +
                "staffId=" + id +
                ", position='" + position + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", salary=" + salary + // Fix: Include salary in toString
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
        return id == staff.id && position.equals(staff.position) && hireDate.equals(staff.hireDate) && endDate.equals(staff.endDate) && salary == staff.salary;
    }

    public void addAttendance(Attendance attendanceRecord) {
        attendance.add(attendanceRecord);
        System.out.println("Attendance added for student ID: " + attendanceRecord.getPersonId());
    }
    public void checkAttendance() {
        try {
            System.out.println("📌 Attendance Records for Staff " + name + " (ID: " + id + "):");
            for (Attendance record : attendance) {
                System.out.println(record.getDate() + " - " + record.getStatus());
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to check attendance: " + e.getMessage());
        }
    }


    // Method to update attendance for a staff member
    public void updateAttendance(int personId, String date, boolean present) {
        for (Attendance record : attendance) {
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
        for (Attendance record : attendance) {
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
