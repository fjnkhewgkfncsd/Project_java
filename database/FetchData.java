package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import database.signup.UserDAO;
import models.*;
import java.util.Collections;
import java.util.HashMap;

public class FetchData {
    public static boolean isEmailTaken(String email) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Failed to connect to the database.");
            return false;
        }
        String checkEmailQuery = "SELECT COUNT(*) FROM student WHERE email = ?";
        try(PreparedStatement stmt = conn.prepareStatement(checkEmailQuery)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;  // Email is already taken
            }            
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;  // Email is available
}

    public static User validateLogin(String email, String password) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Failed to connect to the database.");
            return null;
        }
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Create and return the User object
                return new User(
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("password"),
                    resultSet.getString("dob"),
                    resultSet.getString("sex").charAt(0),
                    resultSet.getString("role")
                );
            } // Returns true if a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Login failed
    }
    public static void InsertUser(User user){
        Connection conn = DatabaseConnection.getConnection();
        if(conn==null){
            System.out.println("❌ Failed to connect to the database.");
            return;
        }
        UserDAO.insert(user,conn,"user");
        DatabaseConnection.closeConnection();
    }
    public static User fetchUser(User user,String email,String role) {
        Connection conn = DatabaseConnection.getConnection();
        if(conn==null){
            System.out.println("❌ Failed to connect to the database.");
            return null;
        }
        String sql = "SELECT * FROM " + role + " WHERE email = ?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if(role.equals("Student")){
                    return new Student(user,
                    resultSet.getString("major"),
                    resultSet.getInt("gen"),
                    resultSet.getInt("id"),
                    resultSet.getInt("stu_group"),
                    resultSet.getInt("Term"),
                    resultSet.getDouble("School_fee"));
                }else if(role.equals("Staff")){
                    return new Staff(user,
                    resultSet.getString("position"));
                }else{
                    return new Lecturer(user,
                    resultSet.getString("specialization")); 
                }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static List<StudentAttendance> fetchAllStudentAttendances(int course_id, int student_id) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Failed to connect to the database.");
            return null;
        }
        String sql = "SELECT * FROM Student_Attendance WHERE course_id = ? AND Stu_id = ?";
        List<StudentAttendance> attendances = new ArrayList<>();

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, course_id);
            statement.setInt(2, student_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                StudentAttendance attendance = new StudentAttendance(
                    resultSet.getInt("stu_id"),
                    resultSet.getString("status"),
                    resultSet.getString("remark"),
                    resultSet.getInt("course_id"),
                    resultSet.getDate("Attendance_date"),
                    resultSet.getTime("Attendance_time"));
                attendances.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return attendances;
    }
    public static int[] fetchCourse_id(int person_id) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Failed to connect to the database.");
            return new int[0]; // Return empty array instead of null
        }
        List<Integer> courseList = new ArrayList<>();
        String sql = "SELECT Course_id FROM courseenrollment WHERE student_id = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, person_id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int c_id = resultSet.getInt("course_id"); // Directly get int
                courseList.add(c_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert List to an int[]
        return courseList.stream().mapToInt(i -> i).toArray();
    }
    public static List<Course> fetchCourse(int[] courseIds,Student student) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Failed to connect to the database.");
            return new ArrayList<>(); // Return an empty list instead of null
        }
        if (courseIds.length == 0) {
            return new ArrayList<>(); // No courses to fetch
        }
        // Create dynamic placeholders (?, ?, ?) for IN clause
        String placeholders = String.join(",", Collections.nCopies(courseIds.length, "?"));
        String sql = "SELECT * FROM Course WHERE course_code IN (" + placeholders + ")";
        
        List<Course> courses = new ArrayList<>();
        Map<Integer, Lecturer> lecturerCache = new HashMap<>(); // Cache lecturers to avoid redundant queries

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            for (int i = 0; i < courseIds.length; i++) {
                statement.setInt(i + 1, courseIds[i]);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int lecturerId = resultSet.getInt("lecturer_id");
                // Fetch lecturer details only once (cache mechanism)
                Lecturer lecturer = lecturerCache.getOrDefault(lecturerId, fetchLecturer(lecturerId));
                lecturerCache.putIfAbsent(lecturerId, lecturer);
                Course course = new Course(
                    resultSet.getInt("course_code"),
                    resultSet.getString("course_name"),
                    resultSet.getInt("credit_hours"),
                    lecturer,
                    rs.getString("schedule"),
                    rs.getString("classroom")
                );
                courses.add(course);
                student.setCourse(courses);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    public static Lecturer fetchLecturer(int person_id) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Failed to connect to the database.");
            return null;
        }
        String sql = "SELECT * FROM Lecturer WHERE lecturer_id = ?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1, person_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("password"),
                    resultSet.getString("dob"),
                    resultSet.getString("sex").charAt(0),
                    resultSet.getString("role")
                );
                return new Lecturer(user,
                    resultSet.getString("specialization"),
                    resultSet.getDouble("salary"),
                    resultSet.getInt("lecturer_id"));
            } 
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
