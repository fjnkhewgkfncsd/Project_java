
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import exceptions.CheckEmptyStringException;
import models.User;
import database.FetchData;

public class LoginForm extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;
    private JLabel messageLabel;

    public LoginForm() {
        setTitle("Login Form");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the form full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Create a panel for the LOG IN button and place it at the top
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 3)); // 3px from the top
        topPanel.setOpaque(false); // Make the panel transparent
        loginButton = new CircularButton("LOG IN");
        topPanel.add(loginButton);
        add(topPanel, BorderLayout.NORTH);

        // Create the main panel for the rest of the components
        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw light gray background
                setBackground(Color.LIGHT_GRAY);
            }
        };
        panel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.BLUE);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(messageLabel, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0); // Adjust the spacing
        signUpButton = new CircularButton("SIGN UP");
        panel.add(signUpButton, gbc);

        add(panel, BorderLayout.CENTER);

        loginButton.addActionListener(new LoginActionListener());
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the UserForm for sign up
                UserForm userForm = new UserForm();
                userForm.setVisible(true);
                dispose(); // Close the LoginForm
            }
        });
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handleLogin();
        }

        private void handleLogin() {
            try {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (email.isEmpty() || password.isEmpty()) {
                    throw new CheckEmptyStringException("Input cannot be empty");
                }
// Check login credentials
                if (FetchData.validateLogin(email,password)) {
                    messageLabel.setText("Login successful!");
                    messageLabel.setForeground(Color.BLUE);
                } else {
                    messageLabel.setText("Invalid email or password.");
                    messageLabel.setForeground(Color.RED);
                }
            } catch (CheckEmptyStringException ex) {
                messageLabel.setText(ex.getMessage());
                messageLabel.setForeground(Color.BLUE);
            } catch (Exception ex) {
                messageLabel.setText("An error occurred.");
                messageLabel.setForeground(Color.RED);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }

    // Custom circular button class
    private static class CircularButton extends JButton {
        public CircularButton(String label) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(Color.WHITE); // Set text color to white
            setBackground(Color.BLUE); // Set background color to blue
            setOpaque(false);
            setFont(new Font("Arial", Font.BOLD, 18)); // Set font size for the button text
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(getBackground().darker());
            } else {
                g.setColor(getBackground());
            }
            g.fillOval(0, 0, getWidth(), getHeight());

            // Ensure the text is visible
            g.setColor(getForeground());
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            int textHeight = fm.getAscent();
            g.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 3);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(getBackground().darker());
            g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(90, 90); // Revert size to usual
        }

        @Override
        public boolean contains(int x, int y) {
            int radius = getWidth() / 2;
            return (x - radius) * (x - radius) + (y - radius) * (y - radius) <= radius * radius;
        }
    }
}