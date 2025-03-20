package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Staff;

public class StaffForm extends JFrame {
    private JButton submitAttendanceButton, viewAttendanceButton;

    public StaffForm(Staff staff) {
        setTitle("Staff Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the form full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

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

        panel.add(submitAttendanceButton);
        panel.add(viewAttendanceButton);

        add(panel);

        submitAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StaffAttendanceForm().setVisible(true);
                dispose();
            }
        });

        viewAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStaffAttendance().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        // SwingUtilities.invokeLater(() -> new StaffForm().setVisible(true));
    }
}
