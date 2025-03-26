package database.signup;
import models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.DatabaseConnection;

public class StaffDAO extends UserDAO {
    public void insertStaff(Staff staff, String tableName) {
        String insertQuery = "UPDATE staff SET position=? WHERE email=?"; // Insert new record
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction
            
            super.insert(staff, conn, tableName); // Insert core user data (including email)
            
            try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setString(1, staff.getPosition());
                stmt.setString(2, staff.getEmail());
                stmt.executeUpdate();
            }
            conn.commit(); // Commit transaction
            System.out.println("✅ Staff added successfully!");
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
