package models;
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
    //toString 
    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", Department_id='" + Department_id + '\'' +
                ", HOD='" + HOD + '\'' +
                '}';
    }
    //equals
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Department d = (Department) obj;
        return d.Department_id == this.Department_id;
    }
}

