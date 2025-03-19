package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffAttendanceForm extends JFrame {
    private JTextField staffIdField;
    private JCheckBox presentCheckBox;
    private JButton submitButton, backButton, viewAttendanceButton;

    public StaffAttendanceForm() {
        setTitle("Submit Attendance");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel staffIdLabel = new JLabel("Staff ID:");
        staffIdLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(staffIdLabel, gbc);

        staffIdField = new JTextField(30);
        staffIdField.setFont(new Font("SansSerif", Font.PLAIN, 28));
        gbc.gridx = 1;
        panel.add(staffIdField, gbc);

        JLabel presentLabel = new JLabel("Present:");
        presentLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(presentLabel, gbc);

        presentCheckBox = new JCheckBox();
        presentCheckBox.setPreferredSize(new Dimension(30, 40));
        presentCheckBox.setBackground(new Color(240, 240, 240));
        gbc.gridx = 1;
        panel.add(presentCheckBox, gbc);

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

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String staffId = staffIdField.getText().trim();
                if (staffId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a Staff ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Attendance submitted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        viewAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStaffAttendance().setVisible(true);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StaffForm().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StaffAttendanceForm().setVisible(true));
    }

    private static class StyledButton extends JButton {
        public StyledButton(String text) {
            super(text);
            setFont(new Font("SansSerif", Font.BOLD, 18));
            setForeground(Color.BLACK);
            setBackground(new Color(65, 105, 225)); // Royal Blue
            setBorderPainted(false);
            setFocusPainted(false);
            setPreferredSize(new Dimension(140, 45));
    
            // Hover Effect
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(72, 118, 255)); // Lighter blue on hover
                }
    
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(65, 105, 225)); // Back to original
                }
    
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(39, 64, 139)); // Darker blue on click
                }
    
                @Override
                public void mouseReleased(java.awt.event.MouseEvent evt) {
                    setBackground(new Color(72, 118, 255)); // Lighter blue
                }
            });
        }
    }
    
}
