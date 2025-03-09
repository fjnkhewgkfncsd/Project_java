import java.util.ArrayList;

public class Lecturer extends User {
    private static int totalLecturers = 0  ;  // Static variable to track total number of lecturers
    private int id;
    private String specialization;
    private double salary;
    private ArrayList<Attendance> attendance;  
    private static ArrayList<Lecturer> lecturerList = new ArrayList<Lecturer>();

    public Lecturer(User s, String specialization, double salary) {
        super(s.name, s.email, s.password, s.password, s.dob, s.gender,s.address);
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


    public static ArrayList<Lecturer> getLecturerList() {
        return lecturerList;
    }


    public void submitAttendance(String username, String password) {
        try {
            if (login(username, password)) {
                System.out.println("Attendance submitted successfully.");
            }
        } catch (LoginFailedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkAttendance(String username, String password) {
        try {
            if (login(username, password)) {
                System.out.println("Checking attendance...");
            }
        } catch (LoginFailedException e) {
            System.out.println(e.getMessage());
        }
    }
    // Method to add attendance for a student
    public void addAttendance(Attendance attendanceRecord) {
        attendance.add(attendanceRecord);
        System.out.println("Attendance added for student ID: " + attendanceRecord.getPersonId());
    }

    // Method to update attendance for a student
    public void updateAttendance(int personId, String date, boolean present) {
        for (Attendance record : attendance) {
            if (record.getPersonId() == personId && record.getDate().toString().equals(date)) {
                record.setStatus(present ? "Present" : "Absent");
                System.out.println("Attendance updated for student ID: " + personId);
                return;
            }
        }
        System.out.println("Attendance record not found for student ID: " + personId);
    }

    // Method to get attendance for a student
    public ArrayList<Attendance> getAttendance(int personId) {
        ArrayList<Attendance> studentAttendance = new ArrayList<>();
        for (Attendance record : attendance) {
            if (record.getPersonId() == personId) {
                studentAttendance.add(record);
            }
        }
        return studentAttendance;
    }
    @Override
    public String toString(){
        return super.toString() + "Lecturer{" +
                "id=" + id +
                ", specialization='" + specialization + '\'' +
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
        Lecturer l = (Lecturer) obj;
        return l.id==this.id;
    }
}
