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
    private JTextField idField, nameField, schoolFeeField; // Updated field declarations
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JButton backButton; // Add back button

    public ManageStudentsForm() {
        setTitle("Manage Students");
        setSize(1000, 700); // Adjusted size for better layout
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background color
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // Adjusted layout for 3 rows
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));
        inputPanel.setBackground(new Color(255, 255, 255)); // White background

        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField(15);
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField(15);
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("School Fee:")); // Added School Fee label
        schoolFeeField = new JTextField(15);       // Added School Fee input field
        inputPanel.add(schoolFeeField);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 20, 10)); // Adjusted layout for better alignment
        buttonPanel.setBackground(new Color(240, 240, 240)); // Match main panel background

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
                viewStudent();
            }
        });
        buttonPanel.add(viewButton);

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

        // Add button panel to the bottom of the layout
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Student Records"));
        tablePanel.setBackground(new Color(255, 255, 255)); // White background

        tableModel = new DefaultTableModel(new String[]{"Student ID", "Name", "School Fee"}, 0); // Updated table columns
        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(25); // Increase row height for better readability
        JScrollPane scrollPane = new JScrollPane(studentTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Move table panel to the center
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Add main panel to the frame
        add(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 40)); // Comfortable button size
        button.setBackground(new Color(70, 130, 180)); // Steel blue background
        button.setForeground(Color.BLACK); // Black text
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font for better visibility
        return button;
    }

    private void addStudent() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String schoolFee = schoolFeeField.getText().trim(); // Updated variable

        if (id.isEmpty() || name.isEmpty() || schoolFee.isEmpty()) { // Updated validation
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO student (id, name, school_fee) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.setString(2, name);
            stmt.setDouble(3, Double.parseDouble(schoolFee));
            stmt.executeUpdate();

            tableModel.addRow(new Object[]{id, name, schoolFee});
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
        String schoolFee = schoolFeeField.getText().trim(); // Updated variable

        if (id.isEmpty() || name.isEmpty() || schoolFee.isEmpty()) { // Updated validation
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE student SET name = ?, school_fee = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, Double.parseDouble(schoolFee));
            stmt.setInt(3, Integer.parseInt(id));
            stmt.executeUpdate();

            tableModel.setValueAt(id, selectedRow, 0);
            tableModel.setValueAt(name, selectedRow, 1);
            tableModel.setValueAt(schoolFee, selectedRow, 2); // Updated column index
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

    private void viewStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student record to view.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM student WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String schoolFee = String.valueOf(rs.getDouble("school_fee"));
                JOptionPane.showMessageDialog(this, "Student Details:\nID: " + id + "\nName: " + name + "\nSchool Fee: " + schoolFee); // Updated message
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error viewing student.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        schoolFeeField.setText(""); // Updated field clearing
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageStudentsForm().setVisible(true));
    }
}
