package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Project_java_university_management_system"; // Change to your DB name
    private static final String USER = "root"; 
    private static final String PASSWORD = "1234";
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Connect to the database
                conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("✅ Database Connected Successfully!");
            } catch (ClassNotFoundException e) {
                System.out.println("❌ JDBC Driver not found: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("❌ Database Connection Error: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;  // Reset connection instance
                System.out.println("🔌 Database Connection Closed.");
            } catch (SQLException e) {
                System.out.println("❌ Error Closing Connection: " + e.getMessage());
            }
        }
    }
}
