package GUI.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminForm extends JFrame {
    private JButton manageStaffButton, manageStudentsButton, manageCoursesButton, manageLecturesButton;
    private JButton backButton; // Add back button

    public AdminForm() {
        setTitle("Admin Dashboard");
        setSize(800, 600); // Increase the window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for better alignment
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Add more padding around the panel
        panel.setBackground(new Color(240, 240, 240)); // Set a light background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Add more padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add a title label
        JLabel titleLabel = new JLabel("Admin Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32)); // Increase font size for the title
        titleLabel.setForeground(new Color(50, 50, 150)); // Set a custom color for the title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span the title across two columns
        panel.add(titleLabel, gbc);

        // Initialize buttons
        manageStaffButton = new JButton("Manage Staff");
        manageStudentsButton = new JButton("Manage Students");
        manageCoursesButton = new JButton("Manage Courses");
        manageLecturesButton = new JButton("Manage Lectures");
        backButton = new JButton("Back"); // Initialize back button

        // Set font size and dimensions for the buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 22); // Increase font size
        Dimension buttonSize = new Dimension(300, 50); // Increase button size
        manageStaffButton.setFont(buttonFont);
        manageStaffButton.setPreferredSize(buttonSize);
        manageStudentsButton.setFont(buttonFont);
        manageStudentsButton.setPreferredSize(buttonSize);
        manageCoursesButton.setFont(buttonFont);
        manageCoursesButton.setPreferredSize(buttonSize);
        manageLecturesButton.setFont(buttonFont);
        manageLecturesButton.setPreferredSize(buttonSize);
        backButton.setFont(buttonFont);
        backButton.setPreferredSize(buttonSize);

        // Add buttons to the panel (two buttons per row)
        gbc.gridwidth = 1; // Reset grid width
        gbc.gridy = 1;
        gbc.gridx = 0;
        panel.add(manageStaffButton, gbc);
        gbc.gridx = 1;
        panel.add(manageStudentsButton, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        panel.add(manageCoursesButton, gbc);
        gbc.gridx = 1;
        panel.add(manageLecturesButton, gbc);
        gbc.gridy = 3; // Place back button below other buttons
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(backButton, gbc);

        add(panel);

        manageStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open staff management form
                new ManageStaffForm().setVisible(true);
                dispose();
            }
        });

        manageStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open student management form
                new ManageStudentsForm().setVisible(true);
                dispose();
            }
        });

        manageCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open course management form
                new ManageCoursesForm().setVisible(true);
                dispose();
            }
        });

        manageLecturesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open lecture management form
                new ManageLecturesForm().setVisible(true);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the AdminForm
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminForm().setVisible(true));
    }
}
