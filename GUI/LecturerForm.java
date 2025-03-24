package GUI;

import javax.swing.*;

import GUI.Components.ViewlecturerAttendance;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Lecturer;

public class LecturerForm extends JFrame {
    private JButton submitAttendanceButton, viewAttendanceButton;

    public LecturerForm(Lecturer lecturer) {
        setTitle("Lecturer Dashboard");
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
        panel.setBackground(new Color(44, 62, 80)); // Dark background

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
        submitAttendanceButton.setBackground(new Color(52, 152, 219)); // Blue
        submitAttendanceButton.setForeground(Color.WHITE);
        viewAttendanceButton.setBackground(new Color(46, 204, 113)); // Green
        viewAttendanceButton.setForeground(Color.WHITE);

        // Border styling
        submitAttendanceButton.setFocusPainted(false);
        viewAttendanceButton.setFocusPainted(false);

        panel.add(submitAttendanceButton);
        panel.add(viewAttendanceButton);

        add(panel);

        submitAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new  AttendanceLecturer(lecturer).setVisible(true);
                dispose();
            }
        });

        viewAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewlecturerAttendance(lecturer).setVisible(true);
                dispose();
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> new LecturerForm().setVisible(true));
    }
}
