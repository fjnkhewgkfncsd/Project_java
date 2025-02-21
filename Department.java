public class Department {
    static int totalDepartments = 0;
    private String name;
    private String location;
    private String Department_id;
    private String HOD;
    //cons
    public Department(String name, String location, String Department_id, String HOD) {
        this.name = name;
        this.location = location;
        this.Department_id = Department_id;
        this.HOD = HOD;
        totalDepartments++;
    }

}

