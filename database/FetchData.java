package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileWriter;
import java.io.IOException;

public class FetchData {
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
}
