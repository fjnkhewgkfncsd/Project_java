package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Staff;
import GUI.Components.ViewStaffAttendance;

public class StaffForm extends JFrame {
    private JButton submitAttendanceButton, viewAttendanceButton;

    public StaffForm(Staff staff) {
        setTitle("Staff Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size to half of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setSize(width, height);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.setBackground(new Color(34, 45, 65)); // Updated dark background

        submitAttendanceButton = new JButton("Submit Attendance");
        viewAttendanceButton = new JButton("View Attendance");

        // Set font size for the buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        submitAttendanceButton.setFont(buttonFont);
        viewAttendanceButton.setFont(buttonFont);

        // Set preferred size for the buttons
        Dimension buttonSize = new Dimension(300, 100);
        submitAttendanceButton.setPreferredSize(buttonSize);
        viewAttendanceButton.setPreferredSize(buttonSize);

        // Button colors
        submitAttendanceButton.setBackground(new Color(241, 196, 15)); // Yellow
        submitAttendanceButton.setForeground(Color.BLACK);
        viewAttendanceButton.setBackground(new Color(39, 174, 96)); // Green
        viewAttendanceButton.setForeground(Color.BLACK);

        // Border styling
        submitAttendanceButton.setFocusPainted(false);
        viewAttendanceButton.setFocusPainted(false);

        panel.add(submitAttendanceButton);
        panel.add(viewAttendanceButton);

        add(panel);

        submitAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AttendanceStaff(staff).setVisible(true); // Pass staff object
                dispose();
            }
        });

        viewAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStaffAttendance(staff).setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        // Example usage
        // SwingUtilities.invokeLater(() -> new StaffForm(new Staff()).setVisible(true));
    }
}
