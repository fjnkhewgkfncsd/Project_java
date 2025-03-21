package database.signup;
import models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.DatabaseConnection;

public class StudentDAO extends UserDAO {
    public void insertStudent(Student student,String tableName) {
        String query = "UPDATE student SET Major=?, Gen=? WHERE email=?"; // Use email as the identifier
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction
            super.insert(student, conn,tableName); // Insert core data (including email)
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, student.getMajor());
                stmt.setInt(2, student.getGen());
                stmt.setString(3, student.getEmail()); // Match by email
                stmt.executeUpdate();
            }
            conn.commit(); // Commit transaction
            System.out.println("✅ Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback on error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
}

