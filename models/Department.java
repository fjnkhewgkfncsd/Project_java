package models;

import java.util.ArrayList;
import java.util.List;

public class Department {
    static int totalDepartments = 0;
    private String name;
    private String Department_id;
    private String HOD;

    private static List<Department> departments = new ArrayList<>();
    //cons
    public Department(String name, String location, String Department_id, String HOD) {
        this.name = name;
        this.Department_id = Department_id;
        this.HOD = HOD;
        totalDepartments++;
    }
    public static Department getDepartmentByName(String name) {
        for (Department department : departments) {
            if (department.name.equalsIgnoreCase(name)) {
                return department;
            }
        }
        return null; // Return null if no matching department is found
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

