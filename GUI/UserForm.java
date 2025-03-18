package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import models.*;
import exceptions.*;

public class UserForm extends JFrame {
    private JTextField nameField, emailField, dobField, phoneField, 
                      majorField, genField, positionField, specializationField;
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
        setLayout(new BorderLayout());

        // Main Panel Setup
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add Components to Main Panel
        addFormRow(mainPanel, gbc, "Role:", roleComboBox = new JComboBox<>(new String[]{"Student", "Staff", "Lecturer"}));
        addFormRow(mainPanel, gbc, "Name:", nameField = new JTextField(20));
        addFormRow(mainPanel, gbc, "Email:", emailField = new JTextField(20));
        addFormRow(mainPanel, gbc, "Date of Birth (YYYY-MM-DD):", dobField = new JTextField(20));
        addFormRow(mainPanel, gbc, "Sex:", sexComboBox = new JComboBox<>(new String[]{"M", "F"}));
        addFormRow(mainPanel, gbc, "Phone:", phoneField = new JTextField(20));
        addFormRow(mainPanel, gbc, "Password:", passwordField = new JPasswordField(20));

        // Extra Fields Panel
        gbc.gridwidth = 2;
        extraFieldsPanel = new JPanel(new GridBagLayout());
        mainPanel.add(extraFieldsPanel, gbc);
        gbc.gridwidth = 1;

        // Submit Button Panel
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);

        // Message Label
        messageLabel = new JLabel(" ", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);

        // Container for Main Content
        JPanel container = new JPanel(new BorderLayout());
        container.add(mainPanel, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.CENTER);
        container.add(messageLabel, BorderLayout.SOUTH);

        // Registration Link
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel registerLabel = new JLabel("Not a user? Register Here");
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(Color.BLUE);
        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
                new LoginFormGUI().setVisible(true);
            }
        });
        bottomPanel.add(registerLabel);

        // Add Components to Frame
        add(container, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event Listeners
        roleComboBox.addActionListener(e -> updateFields());
        submitButton.addActionListener(e -> handleSubmit());

        updateFields();
    }

    // Helper method to add form rows
    private void addFormRow(JPanel panel, GridBagConstraints gbc, String labelText, JComponent component) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(component, gbc);
    }

    // Update dynamic fields based on role
    private void updateFields() {
        extraFieldsPanel.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        String role = (String) roleComboBox.getSelectedItem();
        if ("Student".equals(role)) {
            addExtraField("Major:", majorField = new JTextField(15));
            addExtraField("Gen:", genField = new JTextField(15));
        } else if ("Staff".equals(role)) {
            addExtraField("Position:", positionField = new JTextField(15));
        } else if ("Lecturer".equals(role)) {
            addExtraField("Specialization:", specializationField = new JTextField(15));
        }
        extraFieldsPanel.revalidate();
        extraFieldsPanel.repaint();
    }

    // Helper method for extra fields
    private void addExtraField(String labelText, JTextField textField) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        extraFieldsPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        extraFieldsPanel.add(textField, gbc);
    }

    // Handle form submission
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
            if(role.equals("Student")){
                String major = majorField.getText().trim();
                String gen = genField.getText().trim();
                int genInt = Integer.parseInt(gen);
                Student student = new Student(user,major,genInt);
                student.signUp(student); 
            }else if(role.equals("staff")){
                String position = positionField.getText().trim();
                // Staff staff = new Staff(user,position);
                // staff.signUp();
            }else{
                String specialization = specializationField.getText().trim();
                // Lecturer lecturer = new Lecturer(user,specialization);
                // lecturer.signUp();
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