public class themain {
    public static void main(String[] args) {
            // Create an Address object
            Address address = new Address("Province", "District", "Commune", "Quartier", "StreetNumber", "HomeNumber");
    
            // Create a User object
            User user = new User("John Doe", "john.doe@example.com", "1234567890", "password123", "01-01-2000", 'M', address);
    
            // Create a Student object using the User object
            Student student = new Student(user);
            User user1 = new User("John Doe", "john.doe@example.com", "1234567890", "password123", "01-01-2000", 'M', address);
            // Print the Student object to verify
            System.out.println(student);
            // Lecturer lecturer = new Lecturer("Dr. Jane Doe", "john.doe@example.com", "1234567890", "password123", "01-01-2000", 'M', address);
            // Lecturer lecturer = new Lecturer(user1, "Computer Science", 5000);
            // Course course1 = new Course("CS101", "Introduction to Computer Science","3", "Dr. Jane Doe", "Monday 9:00 AM - 11:00 AM", "Room 101");
            // Course course2 = new Course("MATH101", "Calculus I");
            // student.getCourses().add(course1);
            // student.getCourses().add(course2);
            StudentAttendance attendance1 = new StudentAttendance(student.getId(), "CS101", "Present", "No remarks");
            StudentAttendance attendance2 = new StudentAttendance(student.getId(), "MATH101", "Absent", "Sick");
            // Add attendance records to the Student object
            student.getAttendance().add(attendance1);
            student.getAttendance().add(attendance2);
            student.checkAttendance();
            }
}
