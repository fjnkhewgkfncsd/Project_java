package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {
        Connection conn = MySqlConnection.connect();
        if (conn != null) {
            String sql = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, 1);
                pstmt.setString(2, "John Doe");
                pstmt.setInt(3, 20);
                pstmt.executeUpdate();
                System.out.println("Data inserted successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
