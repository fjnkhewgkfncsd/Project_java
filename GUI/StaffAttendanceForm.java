package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.*;
import GUI.*;
import models.Attendance;
import models.Staff;

public class StaffAttendanceForm extends JFrame {
    private JTextField staffIdField;
    private JCheckBox presentCheckBox;
    private JButton submitButton, backButton, viewAttendanceButton;
    private StaffForm staffForm;

    public StaffAttendanceForm() {
        setTitle("Submit Attendance");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen mode
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        this.staffForm = staffForm;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240)); // Light background color
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Add padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Staff ID Label and TextField
        JLabel staffIdLabel = new JLabel("Staff ID:");
        staffIdLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(staffIdLabel, gbc);

        staffIdField = new JTextField(30);
        staffIdField.setFont(new Font("SansSerif", Font.PLAIN, 28));
        gbc.gridx = 1;
        panel.add(staffIdField, gbc);

        // Present Label and CheckBox
        JLabel presentLabel = new JLabel("Present:");
        presentLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(presentLabel, gbc);

        presentCheckBox = new JCheckBox();
        presentCheckBox.setPreferredSize(new Dimension(30, 40)); // Size of the checkbox
        presentCheckBox.setBackground(new Color(240, 240, 240)); // Match background color
        gbc.gridx = 1;
        panel.add(presentCheckBox, gbc);

        // Buttons
        submitButton = new StyledButton("Submit");
        backButton = new StyledButton("Back");
        viewAttendanceButton = new StyledButton("View Attendance");

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(submitButton);
        buttonPanel.add(viewAttendanceButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        add(panel);

        // Submit button action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String staffId = staffIdField.getText().trim();
                if (staffId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a Staff ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        int id = Integer.parseInt(staffId);
                        Staff staff = null;
                        for (Staff s : Staff.getStaffList()) {
                            if (s.getId() == id) {
                                staff = s;
                                break;
                            }
                        }
                        if (staff == null) {
                            JOptionPane.showMessageDialog(null, "No staff found with ID: " + staffId, "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        String attendanceStatus = presentCheckBox.isSelected() ? "Present" : "Absent";
                        Attendance attendance = new Attendance(LocalDate.now(), attendanceStatus);
                        staff.submitAttendance(attendance);
                        JOptionPane.showMessageDialog(null, "Attendance submitted for Staff ID: " + staffId, "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Staff ID. Please enter a numeric value.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // View Attendance button action
        viewAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStaffAttendance().setVisible(true); // Open ViewAttendance window
            }
        });

        // Back button action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
                staffForm.setVisible(true); // Show the existing StaffForm instance
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StaffAttendanceForm().setVisible(true));
    }

    // Custom StyledButton class for styling buttons
    private static class StyledButton extends JButton {
        public StyledButton(String text) {
            super(text);
            setFont(new Font("SansSerif", Font.BOLD, 18));
            setForeground(Color.BLACK);
            setBackground(new Color(65, 105, 225)); // Royal Blue
            setBorderPainted(false);
            setFocusPainted(false);
            setPreferredSize(new Dimension(140, 45)); // Button size

            // Hover effect
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(72, 118, 255)); // Lighter blue on hover
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(65, 105, 225)); // Original color
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(39, 64, 139)); // Darker blue on click
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(72, 118, 255)); // Back to lighter blue
                }
            });
        }
    }
}
