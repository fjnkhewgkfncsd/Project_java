import java.util.ArrayList; 
import java.util.List;
public class Student implements manage{
    private String name;
    private static int nextId= 0;
    private int id;
    private String password;
    private String dob;//date of birth
    private char gender;
    private String phoneNumber;
    private String email;
    private List<Course> courses;
    private List<StudentAttendance> attendance;
    private static int totalStudents = 0;

    // Constructor for registration
    public Student(String name, int id, String dob,char gender, String phoneNumber, String email, String password) {
        this.name = name;
        this.id=nextId++;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.totalStudents++;
    }

    // Constructor for login
    public Student(String email, String password) {
        this.email = email;
        this.password = password;
    }
    @Override
    public boolean login(String username,String password){
        if(this.password==password){
            return true;
        }
        return false;
    }
    public boolean signup(String name,String password,String phonenumber,char sex,String dob,String email){
        return true;
    }

    public void submitAttendent(String username,String password){
        return;
    }
    public void checkAttendent(String username,String password){
        return;
    }

    // Getters (Allow reading values)
    public String getName() { return name; }
    public int getId() { return id; }
    public String getDob() { return dob; }
    public char getGender() { return gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }

    // Setters (Allow modifying values)
    public void setName(String name) { this.name = name; }
    public void setDob(String dob) { this.dob = dob; }
    public void setGender(char gender) { this.gender = gender; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }

    // Special setter for Password (extra security)
    /* 
    public void setPassword(String password) {
        if (password.length() >= 6) {  // Example: Simple validation
            this.password = password;
        } else {
            System.out.println("Password must be at least 6 characters long!");
        }
    }
    */
    // Getter for Password (Not recommended for security)
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
