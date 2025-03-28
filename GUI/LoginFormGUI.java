package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import database.*;
import models.*;

public class LoginFormGUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public LoginFormGUI() {
        setTitle("Login Form");
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

        JLabel titleLabel = new JLabel("Login Form", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(titleLabel);
        sidebar.add(Box.createVerticalGlue());

        // Right panel (Main content area)
        JPanel contentPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 172, 237), getWidth(), getHeight(), new Color(0, 102, 204));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        contentPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(15);
        contentPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        contentPanel.add(passwordField, gbc);

        gbc.gridy++;
        JButton loginButton = new JButton("Login");
        contentPanel.add(loginButton, gbc);

        gbc.gridy++;
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        contentPanel.add(messageLabel, gbc);

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);

        loginButton.addActionListener(e -> handleLogin());

        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.insets = new Insets(10, 0, 10, 0);
        gbcBottom.gridx = 0;
        gbcBottom.gridy = 0;

        JLabel registerLabel = new JLabel("Not a user? Register Here");
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
                new UserForm().setVisible(true);
            }
        });

        bottomPanel.add(registerLabel, gbcBottom);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void handleLogin() {
        try {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            User user = new User();
            User userfetch = user.login(email, password);
            if (userfetch != null) {
                User userData = FetchData.fetchUser(userfetch, email, userfetch.getRole());
                JOptionPane.showMessageDialog(this, "Login Successful!");
                atferLogin.atferLoginFormGUI(userData);
            } else {
                messageLabel.setText("Login Failed. Please try again.");
            }
        } catch (Exception ex) {
            messageLabel.setText("An error occurred. Please try again.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFormGUI().setVisible(true));
    }
}
