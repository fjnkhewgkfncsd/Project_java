package GUI.Components;

import GUI.Components.AttendanceDisplay;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import models.*;
import database.FetchData;

public class ViewStaffAttendance extends JFrame {
    private JButton backButton;

    public ViewStaffAttendance(String staffName, int staffId) {
        setTitle("View Attendance");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel header = new JLabel("Attendance Records for " + staffName, SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerPanel.add(header, BorderLayout.NORTH);

        List<Attendance> attendances = FetchData.fetchAllStaffAttendances(staffId);
        AttendanceDisplay attendanceDisplay = new AttendanceDisplay(new ArrayList<>()); // Initialize with empty list

        if (attendances == null || attendances.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No attendance records found.");
        } else {
            attendanceDisplay = new AttendanceDisplay(attendances);
        }

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

    public static void main(String[] args) {
        // Example usage
        SwingUtilities.invokeLater(() -> new ViewStaffAttendance("John Doe", 1));
    }
}
