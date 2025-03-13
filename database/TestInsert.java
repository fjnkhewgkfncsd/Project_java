package database;
import database.StudentDAO;

public class TestInsert {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent("John Doe", "kYi8o@example.com", "1234567890", "password123", "1990-01-01", "Male", "123 Main St");
    }
}
