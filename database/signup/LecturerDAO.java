package database.signup;
import models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.DatabaseConnection;

public class LecturerDAO extends UserDAO {
    public void insertLecturer(Lecturer lecturer, String tableName) {
        String query = "UPDATE lecturers SET specialization=? WHERE email=?"; // Use email as the identifier
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction
            
            super.insert(lecturer, conn, tableName); // Insert core user data (including email)
            
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, lecturer.getSpecialization());
                stmt.setString(3, lecturer.getEmail()); // Match by email
                stmt.executeUpdate();
            }
            conn.commit(); // Commit transaction
            System.out.println("✅ Lecturer added successfully!");
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
