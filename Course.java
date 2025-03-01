import java.util.ArrayList;

public class Course {
    private static int totalCourses = 0;  // Static variable to keep track of total courses
    private String courseCode;
    private String courseName;
    private int creditHours;
    private Lecturer lecturer;
    private String schedule;
    private String classroom;

    private static ArrayList<Course> courseList = new ArrayList<>();

    public Course(String courseCode, String courseName, int creditHours, Lecturer lecturer, String schedule, String classroom) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.lecturer = lecturer;
        this.schedule = schedule;
        this.classroom = classroom;
        totalCourses++;  
    }

    public static int getTotalCourses() {
        return totalCourses;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getClassroom() {
        return classroom;
    }

    public static void addCourse(Course course) {
        courseList.add(course);
    }

    public static void removeCourseByCode(String courseCode) {
        courseList.removeIf(course -> course.getCourseCode().equals(courseCode));
    }

    public static Course getCourseByCode(String courseCode) {
        for (Course course : courseList) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;  
    }

    public static ArrayList<Course> getCourseList() {
        return courseList;
    }

    @Override
    public String toString() {
        return "Course Code: " + courseCode + "\n" +
        "Course Name: " + courseName + "\n" +
        "Credit Hours: " + creditHours + "\n" +
        "Lecturer: " + lecturer.getName() + "\n" +
        "Schedule: " + schedule + "\n" +
        "Classroom: " + classroom;
    }
    //equals
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Course c = (Course) obj;
        return c.courseCode.equals(this.courseCode);
    }
}
