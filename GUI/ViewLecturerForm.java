package GUI;

import javax.swing.*;

import models.Lecturer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLecturerForm extends JFrame {
    private JTextField studentIdField;
    private JButton viewButton, backButton;
    private JTextArea attendanceArea;

    public ViewLecturerForm(Lecturer lecturer) {
        // Set the title and default close operation
        setTitle("View Attendance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size to half of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setSize(width, height);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Create the main panel with GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(44, 62, 80)); // Dark theme
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the "Student ID" label
        JLabel studentIdLabel = new JLabel("Enter Student ID:");
        studentIdLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        studentIdLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(studentIdLabel, gbc);

        // Add the text field for entering the student ID
        studentIdField = new JTextField(20);
        studentIdField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(studentIdField, gbc);

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        // Create the "View" and "Back" buttons
        viewButton = new StyledButton("View Attendance", new Color(52, 152, 219)); // Blue
        backButton = new StyledButton("Back to Dashboard", new Color(231, 76, 60)); // Red

        // Add buttons to the button panel
        buttonPanel.add(viewButton);
        buttonPanel.add(backButton);

        // Add the button panel to the main panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        // Create the text area for displaying attendance records
        attendanceArea = new JTextArea();
        attendanceArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        attendanceArea.setEditable(false); // Make it read-only
        attendanceArea.setMargin(new Insets(10, 10, 10, 10)); // Add padding inside the text area

        // Add the text area inside a scroll pane
        JScrollPane scrollPane = new JScrollPane(attendanceArea);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(scrollPane, gbc);

        // Add the main panel to the frame
        add(panel);

        // Action listener for the "View" button
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText().trim();
                if (studentId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a Student ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Placeholder for fetching attendance records
                    attendanceArea.setText("Fetching attendance records for Student ID: " + studentId + "...");
                }
            }
        });

        // Action listener for the "Back" button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LecturerForm().setVisible(true); // Navigate back to the LecturerForm
                dispose(); // Close the current window
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewLecturerForm().setVisible(true));
    }

    private static class StyledButton extends JButton {
        public StyledButton(String text, Color color) {
            super(text);
            setFont(new Font("SansSerif", Font.BOLD, 18));
            setBackground(color);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorderPainted(false);
            setPreferredSize(new Dimension(200, 50));
        }
    }
}
