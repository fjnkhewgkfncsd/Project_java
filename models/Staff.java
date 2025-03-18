package models;
import java.util.ArrayList;
import java.util.List;
public class Staff extends User{
    protected int staffId;
    String position;
    String hireDate;
    String endDate;
    private double salary;
    private List<Attendance> attendanceList;
    static int totalStaffCount = 0;
    static ArrayList<Staff> staffList = new ArrayList<>();

    public Staff(User s, String position, String hireDate, double salary) {
        super(s.name, s.email, s.phoneNumber, s.password, s.dob, s.gender,s.role);
        this.staffId = totalStaffCount++;
        this.position = position;
        this.hireDate = hireDate;
        this.salary = salary;
        staffList.add(this);
        attendanceList = new ArrayList<>();
    }
    
    //getters
    public int getStaffId() { 
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
    public void submitAttendance(Attendance attendance) {
        try {
            Attendance record = new Attendance(attendance.getDate(), attendance.getTime(), staffId, attendance.getStatus(), attendance.getRemarks());
            attendanceList.add(record);
            System.out.println("✅ Attendance recorded for Staff: " + name);
        } catch (Exception e) {
            System.out.println("❌ Failed to submit attendance: " + e.getMessage());
        }
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
}
