package database.signup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.DatabaseConnection;
import models.Course;

public class CourseDAO {
    public static void insertCourse(Course course){
        Connection conn = DatabaseConnection.getConnection();
        if(conn==null){
            System.out.println("❌ Failed to connect to the database.");
            return;
        }
        String sql = "Insert into Course (course_name,course_code,credit_hours,lecturer_id,schedule,classroom) values (?,?,?,?,?,?)";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, course.getCourseName());
            statement.setInt(2, course.getCourseCode());
            statement.setInt(3, course.getCreditHours());
            statement.setInt(4, course.getLecturer().getId());
            statement.setString(5, course.getSchedule());
            statement.setString(6, course.getClassroom());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConnection.closeConnection();
    }
    public static void main(String[] args) {
    }
}
