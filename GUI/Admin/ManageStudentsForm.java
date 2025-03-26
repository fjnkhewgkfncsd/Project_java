package GUI.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DatabaseConnection;

public class ManageStudentsForm extends JFrame {
    private JTextField idField, majorField, schoolFeeField; // Removed nameField
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public ManageStudentsForm() {
        setTitle("Manage Students");
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

        JLabel titleLabel = new JLabel("Manage Students", JLabel.CENTER);
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
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));
        inputPanel.setBackground(new Color(255, 255, 255));

        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField(15);
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Major:"));
        majorField = new JTextField(15);
        inputPanel.add(majorField);

        inputPanel.add(new JLabel("School Fee:"));
        schoolFeeField = new JTextField(15);
        inputPanel.add(schoolFeeField);

        return inputPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Student Records"));
        tablePanel.setBackground(new Color(255, 255, 255));

        tableModel = new DefaultTableModel(new String[]{"Student ID", "Name", "Sex", "Major", "School Fee"}, 0);
        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton addButton = createStyledButton("Add");
        addButton.addActionListener(e -> addStudent());
        buttonPanel.add(addButton);

        JButton editButton = createStyledButton("Edit");
        editButton.addActionListener(e -> editStudent());
        buttonPanel.add(editButton);

        JButton deleteButton = createStyledButton("Delete");
        deleteButton.addActionListener(e -> deleteStudent());
        buttonPanel.add(deleteButton);

        JButton viewButton = createStyledButton("View");
        viewButton.addActionListener(e -> viewStudents());
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

    private void addStudent() {
        String id = idField.getText().trim();
        String major = majorField.getText().trim();
        String schoolFee = schoolFeeField.getText().trim();

        if (id.isEmpty() || major.isEmpty() || schoolFee.isEmpty()) { // Removed name validation
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO student (id, major, school_fee) VALUES (?, ?, ?)"; // Removed name from SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.setString(2, major);
            stmt.setDouble(3, Double.parseDouble(schoolFee));
            stmt.executeUpdate();

            tableModel.addRow(new Object[]{id, "N/A", "N/A", major, schoolFee}); // Removed name from table row
            clearFields();
            JOptionPane.showMessageDialog(this, "Student added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding student.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student record to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = idField.getText().trim();
        String major = majorField.getText().trim();
        String schoolFee = schoolFeeField.getText().trim();

        if (id.isEmpty() || major.isEmpty() || schoolFee.isEmpty()) { // Removed name validation
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE student SET major = ?, school_fee = ? WHERE id = ?"; // Removed name from SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, major);
            stmt.setDouble(2, Double.parseDouble(schoolFee));
            stmt.setInt(3, Integer.parseInt(id));
            stmt.executeUpdate();

            tableModel.setValueAt(id, selectedRow, 0);
            tableModel.setValueAt(major, selectedRow, 3);
            tableModel.setValueAt(schoolFee, selectedRow, 4); // Removed name from table update
            clearFields();
            JOptionPane.showMessageDialog(this, "Student record updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating student.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student record to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM student WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();

            tableModel.removeRow(selectedRow);
            clearFields();
            JOptionPane.showMessageDialog(this, "Student record deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting student.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewStudents() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT student.id, user.sex, student.major, student.school_fee " +
                         "FROM student INNER JOIN user ON student.email = user.email"; // Removed name from SQL
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0);
            while (rs.next()) {
                int id = rs.getInt("id");
                String sex = rs.getString("sex");
                String major = rs.getString("major");
                double schoolFee = rs.getDouble("school_fee");

                tableModel.addRow(new Object[]{id, "N/A", sex, major, schoolFee}); // Removed name from table row
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving student data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        majorField.setText("");
        schoolFeeField.setText(""); // Removed nameField clearing
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> new ManageStudentsForm().setVisible(true));
    // }
}
