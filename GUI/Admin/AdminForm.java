package GUI.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.UserForm; // Import the UserForm class
import database.DatabaseConnection;

public class AdminForm extends JFrame {
    private JButton manageStaffButton, manageStudentsButton, manageCoursesButton, manageLecturesButton, registerButton, backButton;

    public AdminForm() {
        setTitle("Admin Dashboard");
        setSize(900, 550);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Left panel (Sidebar)
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(45, 52, 54));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        
        JLabel titleLabel = new JLabel("Admin Dashboard", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        manageStaffButton = createSidebarButton("Manage Staff", buttonFont);
        manageStudentsButton = createSidebarButton("Manage Students", buttonFont);
        manageCoursesButton = createSidebarButton("Manage Courses", buttonFont);
        manageLecturesButton = createSidebarButton("Manage Lectures", buttonFont);
        registerButton = createSidebarButton("Register", buttonFont);
        backButton = createSidebarButton("Back", buttonFont);

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(titleLabel);
        sidebar.add(Box.createVerticalStrut(30));
        sidebar.add(manageStaffButton);
        sidebar.add(Box.createVerticalStrut(15)); // Adjust spacing for consistency
        sidebar.add(manageStudentsButton);
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(manageCoursesButton);
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(manageLecturesButton);
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(backButton);
        sidebar.add(Box.createVerticalGlue()); // Push the register button to the bottom
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(registerButton);

        // Right panel (Main content area)
        JPanel contentPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 172, 237), getWidth(), getHeight(), new Color(0, 102, 204));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome, School Management", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.WHITE);
        contentPanel.add(welcomeLabel, BorderLayout.CENTER);

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);

        // Button actions
        manageStaffButton.addActionListener(e -> openNewForm(new ManageStaffForm()));
        manageStudentsButton.addActionListener(e -> openNewForm(new ManageStudentsForm()));
        manageCoursesButton.addActionListener(e -> openNewForm(new ManageCoursesForm()));
        manageLecturesButton.addActionListener(e -> openNewForm(new ManageLecturesForm()));
        registerButton.addActionListener(e -> openNewForm(new UserForm()));
        backButton.addActionListener(e -> dispose());
    }

    private JButton createSidebarButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(55, 66, 75));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(180, 45)); // Ensure uniform button size
        button.setMaximumSize(new Dimension(180, 45)); // Ensure consistent maximum size
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        return button;
    }

    private void openNewForm(JFrame form) {
        form.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminForm().setVisible(true));
    }
}
