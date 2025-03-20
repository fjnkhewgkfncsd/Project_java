package database.signup;
import models.*;
import database.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AttendaceDAO {
    public static void insertStudentAttendance(StudentAttendance attendance,String tableName){
        Connection conn = DatabaseConnection.getConnection();
        if(conn==null){
            System.out.println("❌ Failed to connect to the database.");
            return;
        }
        String sql = "Insert into "+tableName+"_attendance (person_id,status,remark,Attendance_time,Attendance_date) values (?,?,?,?,?)";
        if(tableName.equals("Student")){
            sql = "insert into Student_Attendance (person_id,status,remark,Attendance_time,Attendance_date,course_id) values (?,?,?,?,?,?)";
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
                statement.setString(6, attendance.getCourseId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConnection.closeConnection();
    };
}
