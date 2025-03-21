package GUI.Components;

import GUI.Components.AttendanceDisplay;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import models.*;
import database.FetchData;

public class ViewStudentAttendance extends JFrame {
    private JButton backButton;

    public ViewStudentAttendance(String course,int courseId,Student student) {
        setTitle("View Attendance");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel header = new JLabel("View Attendance for " + course, SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerPanel.add(header, BorderLayout.NORTH);

        List<StudentAttendance> attendances = FetchData.fetchAllStudentAttendances(courseId, student.getId());
        // Attendance Display Panel
        AttendanceDisplay attendanceDisplay = new AttendanceDisplay(attendances);

        // Back Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButton = new JButton("Back");
        backButton.setFont(new Font("SansSerif", Font.PLAIN, 20));
        backButton.addActionListener(e -> goBack());
        buttonPanel.add(backButton);

        // Add components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(attendanceDisplay, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    // Method to handle the back button action
    private void goBack() {
        this.dispose();
        
    }
}