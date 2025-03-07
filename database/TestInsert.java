package database;
import database.StudentDAO;

public class TestInsert {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent("Alice", "alice@example.com", "1234567890", "password123", "2002-05-10", "Female", "123 Main St");
    }
}
