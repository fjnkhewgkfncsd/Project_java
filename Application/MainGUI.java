package Application;

import javax.swing.*;
import GUI.*;
import GUI.Admin.AdminForm;
import GUI.Admin.AdminLoginGUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JButton adminButton, userButton, exitButton;

    public MainGUI() {
        setTitle("Main Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Welcome to School Management System", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Center panel with buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        adminButton = new JButton("Admin Login");
        userButton = new JButton("User Login");
        exitButton = new JButton("Exit");

        adminButton.setFont(new Font("Arial", Font.BOLD, 16));
        userButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));

        buttonPanel.add(adminButton);
        buttonPanel.add(userButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Button actions
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLoginGUI().setVisible(true);
                dispose();
            }
        });

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginFormGUI().setVisible(true);
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}
