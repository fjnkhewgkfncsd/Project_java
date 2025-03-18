package GUI;
import javax.swing.*;

import java.awt.*;
import GUI.Components.*;

public class AttendanceGUI {
    public AttendanceGUI(String courseName) {
        JFrame courseFrame = new JFrame(courseName + " - Details");
        courseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        courseFrame.setSize(800, 500);
        courseFrame.setLocationRelativeTo(null);
        courseFrame.setLayout(new BorderLayout());

        JPanel MainPanel = new JPanel(new BorderLayout());
        MainPanel.setBorder(BorderFactory.createEmptyBorder(10, 130, 10, 130));
        JLabel courenamelabel = new JLabel(courseName, SwingConstants.CENTER);
        courenamelabel.setFont(new Font("Arial", Font.BOLD, 38));
        MainPanel.add(courenamelabel, BorderLayout.NORTH);

        JPanel GroupCourse = new JPanel();
        GroupCourse.setLayout(null); // Disable layout manager
        GroupCourse.setPreferredSize(new Dimension(800, 500)); // Set fixed panel size

        CourseButton submitButton = new CourseButton("Submit Attendance", "A002", "8:30-10");
        submitButton.setBounds(20, 70, 210, 130); // (x, y, width, height)

        CourseButton viewButton = new CourseButton("View Attendance");
        viewButton.setBounds(250, 70, 210, 130); // (x, y, width, height)

        GroupCourse.add(submitButton);
        GroupCourse.add(viewButton);

        MainPanel.add(GroupCourse, BorderLayout.CENTER);

        courseFrame.add(MainPanel);
        courseFrame.setVisible(true);
    }
    public static void main(String[] args) {
        AttendanceGUI gui = new AttendanceGUI("Math");
    }
}
