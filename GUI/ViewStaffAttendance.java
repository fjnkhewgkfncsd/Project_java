package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewStaffAttendance extends JFrame {
    private JTextField staffIdField;
    private JButton viewButton, backButton;
    private JTextArea attendanceArea;

    public ViewStaffAttendance() {
        setTitle("View Staff Attendance");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(250, 250, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel staffIdLabel = new JLabel("Staff ID:");
        staffIdLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(staffIdLabel, gbc);

        staffIdField = new JTextField(20);
        staffIdField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(staffIdField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(250, 250, 250));

        viewButton = new StyledButton("View");
        backButton = new StyledButton("Back");

        buttonPanel.add(viewButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        attendanceArea = new JTextArea();
        attendanceArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        attendanceArea.setEditable(false);
        attendanceArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(attendanceArea);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(scrollPane, gbc);

        add(panel);

        // Button Actions
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String staffId = staffIdField.getText().trim();
                if (staffId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a Staff ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    attendanceArea.setText(getAttendanceRecords(staffId));
                }
            }
        });

        // backButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         new StaffForm().setVisible(true);
        //         dispose(); // Close current window
        //     }
        // });
    }

    private String getAttendanceRecords(String staffId) {
        return "Attendance records for Staff ID: " + staffId + "\n" +
               "Date: 2025-03-01 - Present\n" +
               "Date: 2025-03-02 - Absent\n" +
               "Date: 2025-03-03 - Present\n" +
               "Date: 2025-03-04 - Present\n" +
               "Date: 2025-03-05 - Absent\n";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewStaffAttendance().setVisible(true));
    }

    // Styled Button Class with Hover Effects
    private static class StyledButton extends JButton {
        public StyledButton(String text) {
            super(text);
            setFont(new Font("SansSerif", Font.BOLD, 18));
            setForeground(Color.WHITE);
            setBackground(new Color(100, 149, 237)); // Default blue
            setBorderPainted(false);
            setFocusPainted(false);
            setPreferredSize(new Dimension(150, 50));

            // Hover & Click Effects
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(72, 118, 255)); // Lighter blue on hover
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(100, 149, 237)); // Original blue
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(39, 64, 139)); // Darker blue on click
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(72, 118, 255)); // Light blue
                }
            });
        }
    }
}
