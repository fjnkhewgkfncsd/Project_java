package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class studentDAO {
    public void addStudent(String name, String email, String phone, String password, String dob, String gender, String address) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return; // Stop execution if connection fails

        String sql = "INSERT INTO student (name, email, phone_number, password, dob, gender, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, password);  // You should hash the password in a real application
            pstmt.setString(5, dob);
            pstmt.setString(6, gender);
            pstmt.setString(7, address);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Student added successfully!");
            } else {
                System.out.println("❌ Failed to add student.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
}
