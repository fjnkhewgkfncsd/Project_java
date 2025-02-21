import java.util.ArrayList;

public class Lecturer implements manage {
    private static int totalLecturers = 0;  // Static variable to track total number of lecturers
    private int id;
    private String name;
    private String gender;
    private String email;
    private String address;
    private String phone_num;
    private String specialization;
    private double salary;
    private String password;  
    private ArrayList<Attendance> attendance;  
    private static ArrayList<Lecturer> lecturerList = new ArrayList<>();

    public Lecturer(int id, String name, String gender, String email, String address, String phone_num, String specialization, double salary, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phone_num = phone_num;
        this.specialization = specialization;
        this.salary = salary;
        this.password = password;  
        this.attendance = new ArrayList<>();  
        totalLecturers++;
    }

    public static int getTotalLecturers() {
        return totalLecturers;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getPhoneNum() {
        return phone_num;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public static void addLecturer(Lecturer lecturer) {
        lecturerList.add(lecturer);
    }
    
    public static void removeLecturerById(int id) {
        lecturerList.removeIf(lecturer -> lecturer.getId() == id);
    }

    public static Lecturer getLecturerById(int id) {
        for (Lecturer lecturer : lecturerList) {
            if (lecturer.getId() == id) {
                return lecturer;
            }
        }
        return null;
    }

    // Remove displayAllLecturers method

    public static ArrayList<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public boolean login(String email, String password) {
        return true;
    }

    public boolean signup(String name, String password, String phone_num, char sex, String dob, String email) {
        return true;
    }

    public void submitAttendance(String username, String password) {
        return;
    }

    public void checkAttendance(String username, String password) {
        return;
    }

    @Override
    public String toString() {
        return "Lecturer ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Gender: " + gender + "\n" +
               "Email: " + email + "\n" +
               "Address: " + address + "\n" +
               "Phone Number: " + phone_num + "\n" +
               "Specialization: " + specialization + "\n" +
               "Salary: " + salary;
    }
}
