package GUI;
import javax.swing.*;

import GUI.Components.Header_student;
import GUI.Components.CourseButton;
import models.Student;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Student_main_GUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Student_main_GUI());
    }
    public Student_main_GUI() {
        JFrame frame = new JFrame("Student Main GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new BorderLayout());

        JPanel MainPanel = new JPanel(new BorderLayout());
        MainPanel.setBorder(BorderFactory.createEmptyBorder(10, 200, 10, 200));

        Header_student first = new Header_student("Heng Mengly",10,"softwere enginearing");
        MainPanel.add(first,BorderLayout.NORTH);

        JPanel GroupCourse = new JPanel(new GridLayout(3, 2, 60, 20));
        GroupCourse.add(new CourseButton("Math"));
        GroupCourse.add(new CourseButton("Physics"));
        GroupCourse.add(new CourseButton("Java"));
        GroupCourse.add(new CourseButton("C++"));
        GroupCourse.add(new CourseButton("Databases"));
        MainPanel.add(GroupCourse,BorderLayout.CENTER);

        frame.add(MainPanel);
        frame.setVisible(true);
    }
}
