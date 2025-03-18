package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIConnectorExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}

// First GUI (Login)
class LoginGUI extends JFrame {
    public LoginGUI() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField usernameField = new JTextField(10);
        add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(10);
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        JButton loginButton = new JButton("Login");
        add(loginButton, gbc);

        gbc.gridx = 1;
        JButton signUpButton = new JButton("Sign Up");
        add(signUpButton, gbc);

        // Action Listener to Open Signup GUI
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close login window
                new SignupGUI(); // Open signup window
            }
        });

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
}

// Second GUI (Signup)
class SignupGUI extends JFrame {
    public SignupGUI() {
        setTitle("Sign Up");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        JTextField nameField = new JTextField(10);
        add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        JTextField emailField = new JTextField(10);
        add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(10);
        add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        JButton submitButton = new JButton("Register");
        add(submitButton, gbc);

        gbc.gridx = 1;
        JButton backButton = new JButton("Back");
        add(backButton, gbc);

        // Action Listener to go back to Login GUI
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close signup window
                new LoginGUI(); // Open login window
            }
        });

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
}
