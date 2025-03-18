package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;
import database.signup.UserDAO;
import models.*;

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

    public static boolean validateLogin(String email, String password) {
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Failed to connect to the database.");
            return false;
        }
        String sql = "SELECT * FROM student WHERE email = ? AND password = ?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Login failed
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
}
