package GUI;
import javax.swing.*;
import java.util.List;
import GUI.Components.Header_student;
import GUI.Components.CourseButton;
import models.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Student_main_GUI {
    public Student_main_GUI(Student student) {
        JFrame frame = new JFrame("Student Main GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        JPanel MainPanel = new JPanel(new BorderLayout());
        MainPanel.setBorder(BorderFactory.createEmptyBorder(10, 200, 10, 200));

        Header_student first = new Header_student(student.getName(),student.getGen(),student.getMajor());
        MainPanel.add(first,BorderLayout.NORTH);

        JPanel GroupCourse = new JPanel(new GridLayout(3, 2, 60, 20));
        if(student.getCourses().size()==0){
            JLabel noCourse = new JLabel("No Courses Enrolled",SwingConstants.CENTER);
            noCourse.setFont(new Font("Arial", Font.BOLD, 40));
            MainPanel.add(noCourse,BorderLayout.CENTER);
        }else{
            List<Course> courseList = student.getCourses();
            for(int i=0;i<courseList.size();i++){
                Course course = courseList.get(i);
                GroupCourse.add(new CourseButton(course.getCourseName(),student,course.getCourseCode()));
            }
            MainPanel.add(GroupCourse,BorderLayout.CENTER);
        }
        frame.add(MainPanel);
        frame.setVisible(true);
    }
}
