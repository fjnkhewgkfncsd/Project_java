package database.signup;

import models.Department;
import java.sql.*;
import database.DatabaseConnection;

public class DepartmentDAO {
    public void insertDepartment(Department department) {
        String query = "Insert Into Deaprtment (Name,Department_id,HOD) Values (?,?,?)";
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Failed to connect to the database.");
            return;
        }
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, department.getName());
            stmt.setString(2, department.getId());
            stmt.setString(3, department.getHOD());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
}
