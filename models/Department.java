package models;
public class Department {
    static int totalDepartments = 0;
    private String name;
    private String Department_id;
    private String HOD;
    //cons
    public Department(String name, String location, String Department_id, String HOD) {
        this.name = name;
        this.Department_id = Department_id;
        this.HOD = HOD;
        totalDepartments++;
    }
    //toString 
    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", Department_id='" + Department_id + '\'' +
                ", HOD='" + HOD + '\'' +
                '}';
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return Department_id;
    }
    public String getHOD() {
        return HOD;
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

