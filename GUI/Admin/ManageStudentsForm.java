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
    private JTextField idField, nameField, majorField, schoolFeeField;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JButton backButton;

    public ManageStudentsForm() {
        setTitle("Manage Students");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));
        inputPanel.setBackground(new Color(255, 255, 255));

        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField(15);
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField(15);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Major:"));
        majorField = new JTextField(15);
        inputPanel.add(majorField);

        inputPanel.add(new JLabel("School Fee:"));
        schoolFeeField = new JTextField(15);
        inputPanel.add(schoolFeeField);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton addButton = createStyledButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        buttonPanel.add(addButton);

        JButton editButton = createStyledButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });
        buttonPanel.add(editButton);

        JButton deleteButton = createStyledButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        buttonPanel.add(deleteButton);

        JButton viewButton = createStyledButton("View");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStudents();
            }
        });
        buttonPanel.add(viewButton);

        backButton = createStyledButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminForm().setVisible(true);
                dispose();
            }
        });
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Student Records"));
        tablePanel.setBackground(new Color(255, 255, 255));

        tableModel = new DefaultTableModel(new String[]{"Student ID", "Name", "Sex", "Major", "School Fee"}, 0);
        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        add(mainPanel);
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

    private void addStudent() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String major = majorField.getText().trim();
        String schoolFee = schoolFeeField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || major.isEmpty() || schoolFee.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO student (id, name, major, school_fee) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.setString(2, name);
            stmt.setString(3, major);
            stmt.setDouble(4, Double.parseDouble(schoolFee));
            stmt.executeUpdate();

            tableModel.addRow(new Object[]{id, name, "N/A", major, schoolFee});
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
        String name = nameField.getText().trim();
        String major = majorField.getText().trim();
        String schoolFee = schoolFeeField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || major.isEmpty() || schoolFee.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE student SET name = ?, major = ?, school_fee = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, major);
            stmt.setDouble(3, Double.parseDouble(schoolFee));
            stmt.setInt(4, Integer.parseInt(id));
            stmt.executeUpdate();

            tableModel.setValueAt(id, selectedRow, 0);
            tableModel.setValueAt(name, selectedRow, 1);
            tableModel.setValueAt(major, selectedRow, 3);
            tableModel.setValueAt(schoolFee, selectedRow, 4);
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
            String sql = "SELECT student.id, user.name, user.sex, student.major, student.school_fee " +
                         "FROM student INNER JOIN user ON student.email = user.email";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String major = rs.getString("major");
                double schoolFee = rs.getDouble("school_fee");

                tableModel.addRow(new Object[]{id, name, sex, major, schoolFee});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving student data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        majorField.setText("");
        schoolFeeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageStudentsForm().setVisible(true));
    }
}
