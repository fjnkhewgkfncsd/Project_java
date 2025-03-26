package database.signup;
import models.*;
import database.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import GUI.Student_main_GUI;

public class AttendaceDAO {
    public static void insertStudentAttendance(Attendance attendance,String tableName){
        Connection conn = DatabaseConnection.getConnection();
        if(conn==null){
            System.out.println("❌ Failed to connect to the database.");
            return;
        }
        String sql;
        if (tableName.equals("Student")) {
            sql = "INSERT INTO Student_Attendance (stu_id, status, remark, Attendance_time, Attendance_date, course_id) VALUES (?, ?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO " + tableName + "_attendance (person_id, status, remark, Attendance_time, Attendance_date) VALUES (?, ?, ?, ?, ?)";
        }
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            LocalTime localTime = attendance.getTime();
            java.sql.Time sqlTime = java.sql.Time.valueOf(localTime);
            LocalDate LocalDAte = attendance.getDate();
            java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDAte);
            statement.setInt(1, attendance.getPersonId());
            statement.setString(2, attendance.getStatus());
            statement.setString(3, attendance.getRemarks());
            statement.setTime(4, sqlTime);
            statement.setDate(5, sqlDate);
            if(tableName.equals("Student")){
                StudentAttendance studentAttendance = (StudentAttendance) attendance;
                statement.setInt(6, studentAttendance.getCourseId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConnection.closeConnection();
    };
    public static void insertLecturerAttendance(Attendance attendance){
        Connection conn = DatabaseConnection.getConnection();
        if(conn==null){
            System.out.println("❌ Failed to connect to the database.");
            return;
        }
        String sql = "INSERT INTO Lecturer_Attendance (Lec_id, status, remark, Attendance_time, Attendance_date) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            LocalTime localTime = attendance.getTime();
            java.sql.Time sqlTime = java.sql.Time.valueOf(localTime);
            LocalDate LocalDAte = attendance.getDate();
            java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDAte);
            statement.setInt(1, attendance.getPersonId());
            statement.setString(2, attendance.getStatus());
            statement.setString(3, attendance.getRemarks());
            statement.setTime(4, sqlTime);
            statement.setDate(5, sqlDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConnection.closeConnection();
    };
    public static void insertStaffAttendance(Attendance attendance){
        Connection conn = DatabaseConnection.getConnection();
        if(conn==null){
            System.out.println("❌ Failed to connect to the database.");
            return;
        }
        String sql = "INSERT INTO Staff_Attendance (sta_id, status, remark, Attendance_time, Attendance_date) VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            LocalTime localTime = attendance.getTime();
            java.sql.Time sqlTime = java.sql.Time.valueOf(localTime);
            LocalDate LocalDAte = attendance.getDate();
            java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDAte);
            statement.setInt(1, attendance.getPersonId());
            statement.setString(2, attendance.getStatus());
            statement.setString(3, attendance.getRemarks());
            statement.setTime(4, sqlTime);
            statement.setDate(5, sqlDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConnection.closeConnection();
    };
}
