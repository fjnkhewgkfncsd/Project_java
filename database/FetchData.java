package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;

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

    public static void main(String[] args) {
        // Get database connection
        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ Failed to connect to the database.");
            return;
        }
        // SQL Query to fetch student data
        String fetchQuery = "SELECT * FROM student";
        // Define file name
        String fileName = "students_data.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            // Write file header
            writer.write("ID\tName\t\tEmail\t\t\tPhone\t\tPassword\tDOB\t\tGender\tAddress\n");
            writer.write("--------------------------------------------------------------------------------------------------------\n");
            // Prepare and execute the SQL statement
            PreparedStatement pstmt = conn.prepareStatement(fetchQuery);
            ResultSet resultSet = pstmt.executeQuery();
            // Write each row to the file
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone_number");
                String password = resultSet.getString("password");
                String dob = resultSet.getString("dob");
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                // Write data to the file in tab-separated format
                writer.write(id + "\t" + name + "\t" + email + "\t" + phone + "\t" + password + "\t" + dob + "\t" + gender + "\t" + address + "\n");
            }
            System.out.println("✅ Student data fetched and stored in " + fileName);
            // Close resources
            resultSet.close();
            pstmt.close();
            DatabaseConnection.closeConnection();  // Close DB connection
        } catch (SQLException e) {
            System.out.println("❌ SQL Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
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
}
