package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import database.*;
import models.*;

public class LoginFormGUI extends JFrame {
    public LoginFormGUI() {
        super("Login");
        setSize(520, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        addGuiComponents();
    }

    private void addGuiComponents() {
        // create login label
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(0, 25, 520, 100);
        loginLabel.setFont(new Font("Dialog", Font.BOLD, 40));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(loginLabel);

        // create username label
        JLabel usernameLabel = new JLabel("Email:");
        usernameLabel.setBounds(30, 150, 400, 25);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(usernameLabel);

        // create username text field
        JTextField usernameField = new JTextField();
        usernameField.setBounds(30, 185, 450, 55);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(usernameField);

        // create password label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 335, 400, 25);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(passwordLabel);

        // create password text field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(30, 365, 450, 55);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 24));
        add(passwordField);

        // create login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 18));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setBounds(125, 520, 250, 50);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = usernameField.getText();
                String password = new String(passwordField.getPassword());
                User user = new User();
                User userfetch = user.login(email, password);
                if (userfetch!=null) {
                    User Userdata = FetchData.fetchUser(userfetch,email,userfetch.getRole());
                    JOptionPane.showMessageDialog(LoginFormGUI.this, "Login Successful!");
                    atferLogin.atferLoginFormGUI(Userdata);
                }else{
                    JOptionPane.showMessageDialog(LoginFormGUI.this, "Login Failed. Please try again.");
                }
            }
        });
        add(loginButton);

        // create register label
        JLabel registerLabel = new JLabel("Not a user? Register Here");
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setBounds(125, 600, 250, 30);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginFormGUI.this.dispose();
                new UserForm().setVisible(true);
            }
        });
        add(registerLabel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFormGUI loginForm = new LoginFormGUI();
            loginForm.setVisible(true);
        });
    }

}
