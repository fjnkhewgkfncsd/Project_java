package database.signup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.DatabaseConnection;
import models.*;

public class UserDAO {
    public void insert(User user,Connection conn){
        String tableName = user.getRole();
        String query =  "INSERT INTO " + tableName + " (Name,Email,Phone_number, Password,Dob,Sex) VALUES (?, ?, ?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhoneNumber());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getDob());
            stmt.setString(6, String.valueOf(user.getGender()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
