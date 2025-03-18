package GUI;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import GUI.Components.*;

public class AttendanceGUI {
    AttendanceGUI(String courseName) {
        JFrame courseFrame = new JFrame(courseName + " - Details");
        courseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        courseFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        courseFrame.setLayout(new BorderLayout());

        JPanel MainPanel = new JPanel(new BorderLayout());
        MainPanel.setBorder(BorderFactory.createEmptyBorder(10, 200, 10, 200));
        JLabel courenamelabel = new JLabel(courseName, SwingConstants.CENTER);
        courenamelabel.setFont(new Font("Arial", Font.BOLD, 38));
        MainPanel.add(courenamelabel, BorderLayout.NORTH);

        JPanel GroupCourse = new JPanel(new GridLayout(1, 2, 60, 20));
        GroupCourse.add(new CourseButton("Submit Attendance","A002","8:30-10"));
        GroupCourse.add(new CourseButton("View Attendance"));
        MainPanel.add(GroupCourse, BorderLayout.CENTER);

        courseFrame.add(MainPanel);
        courseFrame.setVisible(true);
    }
    public static void main(String[] args) {
        AttendanceGUI gui = new AttendanceGUI("Math");
    }
}
