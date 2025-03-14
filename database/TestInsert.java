package database;
import database.StudentDAO;

public class TestInsert {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        studentDAO.addStudent("hi hakley", "kYihgo@example.com", "1234g567890", "password123", "1990-01-01", "Male", "123 Main St");
    }
}
