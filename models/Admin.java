package models;

import java.util.List;

public class Admin implements Manage {
    String username;
    String email;
    int phoneNumber;
    private String password;

    public Admin(String username, String email, int phoneNumber, String password) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Login method to validate email and password
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    // Method to sign up a new user (returns null for now)
    public User signup(String name, String password, String phoneNumber, char sex, String dob, String email) {
        return null;
    }

            System.out.println("⚠️ Student already enrolled in this course!");
        }
    }

    public void removeCourseFromStudent(Student student, String courseCode) {
        List<Course> courses = student.getCourses();
        Course courseToRemove = null;
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                courseToRemove = course;
                break;
            }
        }
        if (courseToRemove != null) {
            courses.remove(courseToRemove);
            student.setCourse(courses);
            System.out.println("✅ Course " + courseCode + " removed from student: " + student.getId());
        } else {
            System.out.println("⚠️ Course not found for this student!");
        }
    }

    public void viewStudentCourses(Student student) {
        System.out.println("📌 Courses for Student ID " + student.getId() + ":");
        for (Course course : student.getCourses()) {
            System.out.println("- " + course.getCourseName());
        }
    }

    public void setStudentDepartment(Student student, Department department) {
        student.setDepartment(department);
        System.out.println("✅ Department set for Student ID " + student.getId());
    }

    public void setStudentTerm(Student student, int term) {
        student.setTerm(term);
        System.out.println("✅ Term set to " + term + " for Student ID " + student.getId());
    }

    public void setStudentGeneration(Student student, int gen) {
        student.setGen(gen);
        System.out.println("✅ Generation set to " + gen + " for Student ID " + student.getId());
    }

    public void setStudentMajor(Student student, String major) {
        student.setMajor(major);
        System.out.println("✅ Major set to " + major + " for Student ID " + student.getId());
    }

    public void setStudentSchoolFee(Student student, double fee) {
        student.setSchoolfee(fee);
        System.out.println("✅ School fee set to $" + fee + " for Student ID " + student.getId());
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Admin a = (Admin) obj;
        return a.email.equals(this.email);
    }
}
