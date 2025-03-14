package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserForm extends JFrame {
    private JTextField nameField, emailField, dobField, phoneField, addressField;
    private JPasswordField passwordField;
    private JComboBox<String> sexComboBox;
    private JButton submitButton;
    private JLabel messageLabel;

    public UserForm() {
        setTitle("User Form");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

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

        gbc.gridx = 0; gbc.gridy++;
        panel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        addressField = new JTextField(15);
        panel.add(addressField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        submitButton = new JButton("Submit");
        panel.add(submitButton, gbc);

        gbc.gridx = 1;
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.RED);
        panel.add(messageLabel, gbc);

        add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
    }

    private void handleSubmit() {
        try {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String dob = dobField.getText().trim();
            char sex = sexComboBox.getSelectedItem().toString().charAt(0);
            String phone = phoneField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String address = addressField.getText().trim();

            CheckEmptyStringException.checkEmptyString(name);
            CheckEmptyStringException.checkEmailException(email);
            CheckEmptyStringException.checkDobException(dob);
            CheckEmptyStringException.checkSexException(sex);

            Address userAddress = new Address(address, "Anytown", "CA", "12345", "abc", "hi");
            User newUser = new User(name, password, phone, sex, dob, email, userAddress);

            // Save the new user to a file
            newUser.writeToFile();

            messageLabel.setText("Submission successful!");
            messageLabel.setForeground(Color.GREEN);
        } catch (CheckEmptyStringException ex) {
            messageLabel.setText(ex.getMessage());
            messageLabel.setForeground(Color.RED);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserForm().setVisible(true));
    }
}