package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.*;

public class ManageCoursesForm extends JFrame {
    private JTextField studentIdField, courseIdField, groupField, classroomField, yearField, generationField, departmentField, schoolFeeField;
    private JLabel messageLabel;

    public ManageCoursesForm() {
        setTitle("Manage Courses");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Course Assignment Details"));
        inputPanel.setBackground(new Color(255, 255, 255));

        inputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("Course ID:"));
        courseIdField = new JTextField();
        inputPanel.add(courseIdField);

        inputPanel.add(new JLabel("Group:"));
        groupField = new JTextField();
        inputPanel.add(groupField);

        inputPanel.add(new JLabel("Classroom:"));
        classroomField = new JTextField();
        inputPanel.add(classroomField);

        inputPanel.add(new JLabel("Year:"));
        yearField = new JTextField();
        inputPanel.add(yearField);

        inputPanel.add(new JLabel("Generation:"));
        generationField = new JTextField();
        inputPanel.add(generationField);

        inputPanel.add(new JLabel("Department:"));
        departmentField = new JTextField();
        inputPanel.add(departmentField);

        inputPanel.add(new JLabel("School Fee:"));
        schoolFeeField = new JTextField();
        inputPanel.add(schoolFeeField);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton assignButton = createStyledButton("Assign Course");
        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignCourse();
            }
        });
        buttonPanel.add(assignButton);

        JButton clearButton = createStyledButton("Clear Fields");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        buttonPanel.add(clearButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Message Panel
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(new Color(240, 240, 240));
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messagePanel.add(messageLabel);

        mainPanel.add(messagePanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    private void assignCourse() {
        String studentId = studentIdField.getText().trim();
        String courseId = courseIdField.getText().trim();
        String group = groupField.getText().trim();
        String classroom = classroomField.getText().trim();
        String year = yearField.getText().trim();
        String generation = generationField.getText().trim();
        String department = departmentField.getText().trim();
        String schoolFee = schoolFeeField.getText().trim();

        if (studentId.isEmpty() || courseId.isEmpty() || group.isEmpty() || classroom.isEmpty() || year.isEmpty() || generation.isEmpty() || department.isEmpty() || schoolFee.isEmpty()) {
            messageLabel.setText("Please fill in all fields.");
            return;
        }

        Student student = Student.getStudentById(studentId);
        Course course = Course.getCourseByCode(courseId);

        if (student == null) {
            messageLabel.setText("Student not found.");
        } else if (course == null) {
            messageLabel.setText("Course not found.");
        } else {
            // Assuming Student class has a method to assign course with additional details
            student.assignCourse(course, group, classroom, year, generation, department, schoolFee);
            messageLabel.setText("Course assigned successfully!");
        }
    }

    private void clearFields() {
        studentIdField.setText("");
        courseIdField.setText("");
        groupField.setText("");
        classroomField.setText("");
        yearField.setText("");
        generationField.setText("");
        departmentField.setText("");
        schoolFeeField.setText("");
        messageLabel.setText("");
    }
}
