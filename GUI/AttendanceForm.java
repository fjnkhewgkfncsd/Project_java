package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendanceForm extends JFrame {
    private JTextField lecturerIdField;
    private JCheckBox presentCheckBox;
    private JButton submitButton, backButton;

    public AttendanceForm() {
        setTitle("Submit Attendance");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lecturerIdLabel = new JLabel("Lecturer ID:");
        lecturerIdLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lecturerIdLabel, gbc);

        lecturerIdField = new JTextField(30);
        lecturerIdField.setFont(new Font("SansSerif", Font.PLAIN, 28));
        gbc.gridx = 1;
        panel.add(lecturerIdField, gbc);

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

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lecturerId = lecturerIdField.getText().trim();
                if (lecturerId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a Lecturer ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Attendance submitted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LecturerForm().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AttendanceForm().setVisible(true));
    }

    private static class StyledButton extends JButton {
        public StyledButton(String text) {
            super(text);
            setFont(new Font("SansSerif", Font.BOLD, 18));
            setForeground(Color.WHITE);
            setBackground(new Color(100, 149, 237));
            setBorderPainted(false);
            setFocusPainted(false);
            setPreferredSize(new Dimension(140, 45));
        }
    }
}
