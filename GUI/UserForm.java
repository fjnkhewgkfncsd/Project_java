package GUI;
import javax.swing.*;

import database.FetchData;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; // Correct import
import models.*;
import exceptions.*;



public class UserForm extends JFrame {
    private JTextField nameField, emailField, dobField, phoneField,majorField, genField, positionField, specializationField;
    private JPasswordField passwordField;
    private JComboBox<String> sexComboBox, roleComboBox;
    private JButton submitButton;
    private JLabel messageLabel;
    private JPanel extraFieldsPanel;

    public UserForm() {
        setTitle("User Form");
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        panel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        roleComboBox = new JComboBox<>(new String[]{"Student", "Staff", "Lecturer"});
        panel.add(roleComboBox, gbc);
        gbc.gridx = 0; gbc.gridy++;

        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        panel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(15);
        panel.add(emailField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Date of Birth (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        dobField = new JTextField(15);
        panel.add(dobField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Sex:"), gbc);
        gbc.gridx = 1;
        sexComboBox = new JComboBox<>(new String[]{"M", "F"});
        panel.add(sexComboBox, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneField = new JTextField(15);
        panel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        extraFieldsPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0; gbc.gridy++;
        panel.add(extraFieldsPanel, gbc);

        roleComboBox.addActionListener(e -> updateFields());
        
        gbc.gridy++;
        submitButton = new JButton("Submit");
        panel.add(submitButton, gbc);
        
        gbc.gridx = 1;
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        panel.add(messageLabel, gbc);

        add(panel);
        updateFields();
        submitButton.addActionListener(e -> handleSubmit());

        JPanel bottomPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout
        GridBagConstraints gbcBottom = new GridBagConstraints();
        gbcBottom.insets = new Insets(10, 0, 10, 0); // Add padding
        gbcBottom.gridx = 0;
        gbcBottom.gridy = 0;

        JLabel registerLabel = new JLabel("Not a user? Register Here");
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose();
                new LoginFormGUI().setVisible(true);
            }
        });
    
        bottomPanel.add(registerLabel, gbcBottom);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateFields() {
        extraFieldsPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        String role = (String) roleComboBox.getSelectedItem();
        if ("Student".equals(role)) {
            extraFieldsPanel.add(new JLabel("Major:"), gbc);
            gbc.gridx = 1;
            majorField = new JTextField(15);
            extraFieldsPanel.add(majorField, gbc);
            gbc.gridx = 0; gbc.gridy++;
            extraFieldsPanel.add(new JLabel("Gen:"), gbc);
            gbc.gridx = 1;
            genField = new JTextField(15);
            extraFieldsPanel.add(genField, gbc);
        } else if ("Staff".equals(role)) {
            extraFieldsPanel.add(new JLabel("Position:"), gbc);
            gbc.gridx = 1;
            positionField = new JTextField(15);
            extraFieldsPanel.add(positionField, gbc);
        } else if ("Lecturer".equals(role)) {
            extraFieldsPanel.add(new JLabel("Specialization:"), gbc);
            gbc.gridx = 1;
            specializationField = new JTextField(15);
            extraFieldsPanel.add(specializationField, gbc);
        }
        extraFieldsPanel.revalidate();
        extraFieldsPanel.repaint();
    }


    private void handleSubmit() {
        try {
            String role = roleComboBox.getSelectedItem().toString().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String dob = dobField.getText().trim();
            char sex = sexComboBox.getSelectedItem().toString().charAt(0);
            String phonenumber = phoneField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String[] inputStrings = {name,password,phonenumber,dob,email,role};
            if(role.equals("Student")){
                String major = majorField.getText().trim();
                String gen = genField.getText().trim();
                inputStrings[inputStrings.length - 2] = major;
                inputStrings[inputStrings.length - 1] = gen;
            }else if(role.equals("Staff")){
                String position = positionField.getText().trim();
                inputStrings[inputStrings.length - 1] = position;
            }else{
                String specialization = specializationField.getText().trim();
                inputStrings[inputStrings.length - 1] = specialization;
            }
            new CheckEmptyStringException(inputStrings);
            new CheckEmptyStringException(name,"[a-zA-Z]+");
            new NumberOnlyException(phonenumber,"[0-9]+");
            CheckEmptyStringException.Checkemailexception(email);
            CheckEmptyStringException.Checkdobexception(dob);
            CheckEmptyStringException.Checksexexception(sex);
            CheckEmptyStringException.Isemailtaken(email);
            User user = new User(name,email,phonenumber,password,dob,sex,role);
            FetchData.InsertUser(user);
            if(role.equals("Student")){
                String major = majorField.getText().trim();
                String gen = genField.getText().trim();
                int genInt = Integer.parseInt(gen);
                Student student = new Student(user,major,genInt);
                student.signUp(student); 
            }else if(role.equals("staff")){
                String position = positionField.getText().trim();
                Staff staff = new Staff(user,position);
                staff.signUp(staff);
            }else{
                String specialization = specializationField.getText().trim();
                Lecturer lecturer = new Lecturer(user,specialization);
                lecturer.signUp(lecturer);
            }
            messageLabel.setText("Submission successful!");
            messageLabel.setForeground(Color.GREEN);
        } catch (CheckEmptyStringException |NumberFormatException ex) {
            messageLabel.setText(ex.getMessage());
            messageLabel.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserForm().setVisible(true));
    }
}