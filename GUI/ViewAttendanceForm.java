package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAttendanceForm extends JFrame {
    private JTextField studentIdField;
    private JButton viewButton, backButton;
    private JTextArea attendanceArea;

    public ViewAttendanceForm() {
        setTitle("View Attendance");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(250, 250, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(studentIdLabel, gbc);

        studentIdField = new JTextField(20);
        studentIdField.setFont(new Font("SansSerif", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(studentIdField, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(250, 250, 250));

        viewButton = new StyledButton("View");
        backButton = new StyledButton("Back");

        buttonPanel.add(viewButton);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);

        attendanceArea = new JTextArea();
        attendanceArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        attendanceArea.setEditable(false);
        attendanceArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(attendanceArea);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(scrollPane, gbc);

        add(panel);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText().trim();
                if (studentId.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a Student ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    attendanceArea.setText(getAttendanceRecords(studentId));
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LecturerForm().setVisible(true);
                dispose();
            }
        });
    }

    private String getAttendanceRecords(String studentId) {
        return "Attendance records for student ID: " + studentId + "\n" +
               "Date: 2025-03-01 - Present\n" +
               "Date: 2025-03-02 - Absent\n" +
               "Date: 2025-03-03 - Present\n" +
               "Date: 2025-03-04 - Present\n" +
               "Date: 2025-03-05 - Absent\n";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewAttendanceForm().setVisible(true));
    }

    private static class StyledButton extends JButton {
        public StyledButton(String text) {
            super(text);
            setFont(new Font("SansSerif", Font.BOLD, 18));
            setBackground(new Color(100, 149, 237));
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setBorderPainted(false);
            setPreferredSize(new Dimension(150, 50));
        }
    }
}
