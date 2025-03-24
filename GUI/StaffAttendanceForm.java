package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Staff;
import models.Attendance;
import database.AttendanceDAO;

public class StaffAttendanceForm extends JFrame {
    private JTextField remarkField;

    public StaffAttendanceForm(Staff staff) {
        setTitle("Submit Attendance");
        setSize(600, 300); // Larger frame size
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use a modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        JLabel headerLabel = new JLabel("Submit Staff Attendance", SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24)); // Bigger font size
        headerLabel.setForeground(new Color(50, 50, 50)); // Dark gray text
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        // Remark Input Panel
        JPanel remarkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        remarkPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        JLabel remarkLabel = new JLabel("Remark:");
        remarkLabel.setFont(new Font("SansSerif", Font.PLAIN, 18)); // Bigger font size
        remarkLabel.setForeground(new Color(50, 50, 50)); // Dark gray text
        remarkField = new JTextField(30); // Wider text field
        remarkField.setFont(new Font("SansSerif", Font.PLAIN, 16)); // Bigger font size
        remarkField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), // Light gray border
            BorderFactory.createEmptyBorder(5, 10, 5, 10) // Padding inside the text field
        ));
        remarkPanel.add(remarkLabel);
        remarkPanel.add(remarkField);

        // Submit Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("SansSerif", Font.PLAIN, 16)); // Smaller font size
        submitButton.setPreferredSize(new Dimension(120, 40)); // Smaller button size
        submitButton.setBackground(new Color(50, 150, 250)); // Blue background
        submitButton.setForeground(Color.WHITE); // White text
        submitButton.setFocusPainted(false); // Remove focus border
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside the button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitAttendance(staff);
            }
        });
        buttonPanel.add(submitButton);

        // Add components to the main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(remarkPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to the frame
        add(mainPanel);

        setVisible(true);
    }

    private void submitAttendance(Staff staff) {
        String remark = remarkField.getText().trim();

        if (remark.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a remark.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save attendance to the database
        Attendance attendance = new Attendance(staff.getId(), "Present", remark);
        AttendanceDAO.insertStaffAttendance(attendance);

        JOptionPane.showMessageDialog(this, "Attendance submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        remarkField.setText(""); // Clear the remark field
    }

    public static void main(String[] args) {
        // Example usage
        // SwingUtilities.invokeLater(() -> {
        //     Staff staff = new Staff(1, "John Doe"); // Replace with actual staff object
        //     new StaffAttendanceForm(staff);
        // });
    }
}
