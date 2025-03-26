package GUI.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DatabaseConnection;

public class ManageCoursesForm extends JFrame {
    private JTextField studentIdField, courseIdField, groupField, classroomField, yearField, generationField, departmentField;
    private JTable coursesTable;
    private DefaultTableModel tableModel;

    public ManageCoursesForm() {
        setTitle("Manage Courses");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Layout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createSidebar(), BorderLayout.WEST);
        mainPanel.add(createContentPanel(), BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(45, 52, 54));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        JLabel titleLabel = new JLabel("Manage Courses", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = createSidebarButton("Back");
        backButton.addActionListener(e -> {
            new AdminForm().setVisible(true);
            dispose();
        });

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(titleLabel);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(backButton);

        return sidebar;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.add(createInputPanel(), BorderLayout.NORTH);
        contentPanel.add(createTablePanel(), BorderLayout.CENTER);
        contentPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        return contentPanel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Course Details"));
        inputPanel.setBackground(new Color(255, 255, 255));

        inputPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField(15);
        inputPanel.add(studentIdField);

        inputPanel.add(new JLabel("Course ID:"));
        courseIdField = new JTextField(15);
        inputPanel.add(courseIdField);

        inputPanel.add(new JLabel("Group:"));
        groupField = new JTextField(15);
        inputPanel.add(groupField);

        inputPanel.add(new JLabel("Classroom:"));
        classroomField = new JTextField(15);
        inputPanel.add(classroomField);

        inputPanel.add(new JLabel("Year:"));
        yearField = new JTextField(15);
        inputPanel.add(yearField);

        inputPanel.add(new JLabel("Generation:"));
        generationField = new JTextField(15);
        inputPanel.add(generationField);

        inputPanel.add(new JLabel("Department:"));
        departmentField = new JTextField(15);
        inputPanel.add(departmentField);

        return inputPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Course Records"));
        tablePanel.setBackground(new Color(255, 255, 255));

        tableModel = new DefaultTableModel(new String[]{"Student ID", "Course ID", "Group", "Classroom", "Year", "Generation", "Department"}, 0);
        coursesTable = new JTable(tableModel);
        coursesTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(coursesTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton addButton = createStyledButton("Add");
        addButton.addActionListener(e -> addCourse());
        buttonPanel.add(addButton);

        JButton editButton = createStyledButton("Edit");
        editButton.addActionListener(e -> editCourse());
        buttonPanel.add(editButton);

        JButton deleteButton = createStyledButton("Delete");
        deleteButton.addActionListener(e -> deleteCourse());
        buttonPanel.add(deleteButton);

        JButton viewButton = createStyledButton("View");
        viewButton.addActionListener(e -> viewCourses());
        buttonPanel.add(viewButton);

        return buttonPanel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 40));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(55, 66, 75));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(180, 45));
        button.setMaximumSize(new Dimension(180, 45));
        button.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        return button;
    }

    private void addCourse() {
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
            String sql = "INSERT INTO courseenrollment (student_id, course_id, group, classroom, year, generation, department) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(studentId));
            stmt.setInt(2, Integer.parseInt(courseId));
            stmt.setString(3, group);
            stmt.setString(4, classroom);
            stmt.setString(5, year);
            stmt.setString(6, generation);
            stmt.setString(7, department);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Course added successfully!");
            viewCourses(); // Refresh table
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding course.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editCourse() {
        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
            String sql = "UPDATE courseenrollment SET group = ?, classroom = ?, year = ?, generation = ?, department = ? WHERE student_id = ? AND course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, group);
            stmt.setString(2, classroom);
            stmt.setString(3, year);
            stmt.setString(4, generation);
            stmt.setString(5, department);
            stmt.setInt(6, Integer.parseInt(studentId));
            stmt.setInt(7, Integer.parseInt(courseId));
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Course updated successfully!");
            viewCourses(); // Refresh table
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating course.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteCourse() {
        int selectedRow = coursesTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int studentId = (int) tableModel.getValueAt(selectedRow, 0);
        int courseId = (int) tableModel.getValueAt(selectedRow, 1);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM courseenrollment WHERE student_id = ? AND course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Course deleted successfully!");
            viewCourses(); // Refresh table
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting course.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> new ManageCoursesForm().setVisible(true));
    // }
}
