// package GUI;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class AttendanceLecturerForm extends JFrame {
//     private JTextField lecturerIdField;
//     private JCheckBox presentCheckBox;
//     private JButton submitButton, backButton;

//     public AttendanceLecturerForm() {
//         setTitle("Submit Attendance");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         // Set the size to half of the screen
//         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//         int width = screenSize.width / 2;
//         int height = screenSize.height / 2;
//         setSize(width, height);

//         // Center the frame on the screen
//         setLocationRelativeTo(null);

//         JPanel panel = new JPanel(new GridBagLayout());
//         panel.setBackground(new Color(240, 240, 240));
//         GridBagConstraints gbc = new GridBagConstraints();
//         gbc.insets = new Insets(20, 20, 20, 20);
//         gbc.fill = GridBagConstraints.HORIZONTAL;

//         // Lecturer ID Label
//         JLabel lecturerIdLabel = new JLabel("Enter Lecturer ID:");
//         lecturerIdLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
//         gbc.gridx = 0;
//         gbc.gridy = 0;
//         gbc.anchor = GridBagConstraints.WEST;
//         panel.add(lecturerIdLabel, gbc);

//         // Lecturer ID Text Field
//         lecturerIdField = new JTextField(20);
//         lecturerIdField.setFont(new Font("SansSerif", Font.PLAIN, 20));
//         gbc.gridx = 1;
//         panel.add(lecturerIdField, gbc);

//         // Present Label
//         JLabel presentLabel = new JLabel("Mark as Present:");
//         presentLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
//         gbc.gridx = 0;
//         gbc.gridy = 1;
//         panel.add(presentLabel, gbc);

//         // Present CheckBox
//         presentCheckBox = new JCheckBox();
//         presentCheckBox.setPreferredSize(new Dimension(30, 30));
//         presentCheckBox.setBackground(new Color(240, 240, 240));
//         gbc.gridx = 1;
//         panel.add(presentCheckBox, gbc);

//         // Submit and Back Buttons
//         submitButton = new StyledButton("Submit");
//         backButton = new StyledButton("Back");

//         gbc.gridx = 0;
//         gbc.gridy = 2;
//         gbc.gridwidth = 2;
//         gbc.anchor = GridBagConstraints.CENTER;
//         JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
//         buttonPanel.setOpaque(false);
//         buttonPanel.add(submitButton);
//         buttonPanel.add(backButton);
//         panel.add(buttonPanel, gbc);

//         add(panel);

//         // Submit Button Action
//         submitButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 String lecturerId = lecturerIdField.getText().trim();
//                 if (lecturerId.isEmpty()) {
//                     JOptionPane.showMessageDialog(null, "Please enter a valid Lecturer ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
//                 } else {
//                     JOptionPane.showMessageDialog(null, "Attendance has been successfully submitted!", "Success", JOptionPane.INFORMATION_MESSAGE);
//                 }
//             }
//         });

//         // Back Button Action
//         backButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 new LecturerForm().setVisible(true);
//                 dispose();
//             }
//         });
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> new AttendanceLecturerForm().setVisible(true));
//     }

//     private static class StyledButton extends JButton {
//         public StyledButton(String text) {
//             super(text);
//             setFont(new Font("SansSerif", Font.BOLD, 18));
//             setForeground(Color.WHITE);
//             setBackground(new Color(70, 130, 180)); // Steel Blue color
//             setBorderPainted(false);
//             setFocusPainted(false);
//             setPreferredSize(new Dimension(200, 50));
//         }
//     }
// }
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttendanceLecturerForm extends JFrame {
    private JTextField lecturerIdField;
    private JCheckBox presentCheckBox;
    private JButton submitButton, backButton;

    public AttendanceLecturerForm() {
        setTitle("Submit Attendance");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size to half of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2;
        int height = screenSize.height / 2;
        setSize(width, height);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(44, 62, 80)); // Dark theme
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Lecturer ID Label
        JLabel lecturerIdLabel = new JLabel("Enter Lecturer ID:");
        lecturerIdLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        lecturerIdLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lecturerIdLabel, gbc);

        // Lecturer ID Text Field
        lecturerIdField = new JTextField(20);
        lecturerIdField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridx = 1;
        panel.add(lecturerIdField, gbc);

        // Present Label
        JLabel presentLabel = new JLabel("Mark as Present:");
        presentLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        presentLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(presentLabel, gbc);

        // Present CheckBox
        presentCheckBox = new JCheckBox();
        presentCheckBox.setPreferredSize(new Dimension(30, 30));
        presentCheckBox.setBackground(new Color(44, 62, 80)); // Match background
        gbc.gridx = 1;
        panel.add(presentCheckBox, gbc);

        // Submit and Back Buttons
        submitButton = new StyledButton("Submit", new Color(52, 152, 219)); // Blue
        backButton = new StyledButton("Back", new Color(231, 76, 60)); // Red

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(submitButton);
        buttonPanel.add(backButton);
        panel.add(buttonPanel, gbc);

        add(panel);

        // Submit Button Action
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lecturerId = lecturerIdField.getText().trim();
                if (lecturerId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid Lecturer ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Attendance has been successfully submitted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Back Button Action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LecturerForm().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AttendanceLecturerForm().setVisible(true));
    }

    private static class StyledButton extends JButton {
        public StyledButton(String text, Color color) {
            super(text);
            setFont(new Font("SansSerif", Font.BOLD, 18));
            setForeground(Color.WHITE);
            setBackground(color);
            setBorderPainted(false);
            setFocusPainted(false);
            setPreferredSize(new Dimension(200, 50));
        }
    }
}
