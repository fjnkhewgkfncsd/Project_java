package GUI.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.DatabaseConnection;
import javax.swing.table.DefaultTableModel;

public class ManageCoursesForm extends JFrame {
    private JTextField studentIdField, courseIdField, groupField, classroomField, yearField, generationField, departmentField;
    private JTextArea infoTextArea;
    private JButton backButton; // Add back button
    private JTable coursesTable;
    private DefaultTableModel tableModel;

    public ManageCoursesForm() {
        setTitle("Manage Courses");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
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

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Info Display Panel
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("Information"));
        infoTextArea = new JTextArea(10, 50);
        infoTextArea.setEditable(false);
        infoTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        infoPanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 20, 10)); // Adjust button panel layout for better alignment
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

        // Add back button to the button panel
        backButton = createStyledButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminForm().setVisible(true);
                dispose();
            }
        });
        buttonPanel.add(backButton);

        // Add button to view all course assignments
        JButton viewButton = createStyledButton("View All");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewCourses();
            }
        });
        buttonPanel.add(viewButton);

        // Add button panel to the bottom of the layout
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Move info panel to the center
        mainPanel.add(infoPanel, BorderLayout.CENTER);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Course Assignments"));
        tablePanel.setBackground(new Color(255, 255, 255)); // White background

        tableModel = new DefaultTableModel(new String[]{"Student ID", "Course ID", "Group", "Classroom", "Year", "Generation", "Department"}, 0);
        coursesTable = new JTable(tableModel);
        coursesTable.setRowHeight(25); // Increase row height for better readability
        JScrollPane scrollPaneTable = new JScrollPane(coursesTable);
        tablePanel.add(scrollPaneTable, BorderLayout.CENTER);

        // Move table panel to the center
        mainPanel.add(tablePanel, BorderLayout.CENTER);

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

        if (studentId.isEmpty() || courseId.isEmpty() || group.isEmpty() || classroom.isEmpty() || year.isEmpty() || generation.isEmpty() || department.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO courseenrollment (student_id, course_id) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(studentId));
            stmt.setInt(2, Integer.parseInt(courseId));
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Course assigned successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error assigning course.", "Error", JOptionPane.ERROR_MESSAGE);
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
        infoTextArea.setText("");
    }

    private void viewCourses() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM courseenrollment";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0); // Clear existing rows
            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                int courseId = rs.getInt("course_id");
                String group = rs.getString("group");
                String classroom = rs.getString("classroom");
                String year = rs.getString("year");
                String generation = rs.getString("generation");
                String department = rs.getString("department");

                tableModel.addRow(new Object[]{studentId, courseId, group, classroom, year, generation, department});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving course data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
