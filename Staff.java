import java.util.ArrayList;
import java.util.List;
public class Staff implements Manage{
    private int staffId;
    String name;
    String position;
    String gender;
    String dateOfBirth;  
    String phoneNumber;
    String email;
    private String password;
    String hireDate;
    String endDate;
    private double salary;
    private List<Attendance> attendanceList;

    static int totalStaffCount = 0;
    static ArrayList<Staff> staffList = new ArrayList<>();

    public Staff(String name, String dateOfBirth, String gender, 
                 String position, String phoneNumber, String email, String password, 
                 String hireDate, String endDate, double salary) {
        this.staffId = ++totalStaffCount;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.hireDate = hireDate;
        this.endDate = endDate;
        this.salary = salary;
        staffList.add(this);
    }

    public int getStaffId() { 
        return staffId;
    }

    public static ArrayList<Staff> getStaffList() {
        return staffList;
    }
    public List<Attendance> getattendanceList() {
        return attendanceList;
    }
    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", firstName='" + name + '\'' +
                ", position='" + position + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate='" + hireDate + '\'' +
                ", salary= $" + salary +
                '}';
    }
    public boolean signup(String name,String password,String phonenumber,char sex,String dob,String email){
        return true;
    }
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
}
