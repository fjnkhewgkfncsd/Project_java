import java.util.ArrayList;

public class Lecturer extends User {
    private static int totalLecturers = 0  ;  // Static variable to track total number of lecturers
    private int id;
    private String specialization;
    private double salary;
    private ArrayList<Attendance> attendance;  
    private static ArrayList<Lecturer> lecturerList = new ArrayList<Lecturer>();
    public Lecturer(User s, String specialization, double salary) {
        super(s.name, s.email, s.phoneNumber, s.password, s.dob, s.gender,s.address);
        this.id = totalLecturers++;
        this.specialization = specialization;
        this.salary = salary;
        lecturerList.add(this);
        attendance = new ArrayList<Attendance>();
    }

    public static int getTotalLecturers() {
        return totalLecturers;
    }
    public int getId() {
        return id;
    }
    public String getSpecialization() {
        return specialization;
    }
    public double getSalary() {
        return salary;
    }
    //setters
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public void setSalary(double salary) {
        this.salary = salary;
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
    public String toString(){
        return super.toString() + "Lecturer{" +
                "id=" + id +
                ", specialization='" + specialization + '\'' +
                ", salary=" + salary +
                '}';
    } 
    public boolean equals(Object obj){
        if(obj==this){
            return true;
        }
        if(obj==null || obj.getClass()!=this.getClass()){
            return false;
        }
        Lecturer l = (Lecturer) obj;
        return l.id==this.id;
    }
}
