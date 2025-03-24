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

public class ManageStaffForm extends JFrame {
    private JTextField idField, positionField, salaryField;
    private JTable staffTable;
    private DefaultTableModel tableModel;
    private JButton backButton; // Add back button

    public ManageStaffForm() {
        setTitle("Manage Staff");
        setSize(1000, 700); // Adjusted size for better layout
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background color
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Staff Details"));
        inputPanel.setBackground(new Color(255, 255, 255)); // White background

        inputPanel.add(new JLabel("Staff ID:"));
        idField = new JTextField(15);
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Position:"));
        positionField = new JTextField(15);
        inputPanel.add(positionField);

        inputPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField(15);
        inputPanel.add(salaryField);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 20, 10)); // Adjusted layout for better alignment
        buttonPanel.setBackground(new Color(240, 240, 240)); // Match main panel background

        JButton addButton = createStyledButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStaff();
            }
        });
        buttonPanel.add(addButton);

        JButton editButton = createStyledButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStaff();
            }
        });
        buttonPanel.add(editButton);

        JButton deleteButton = createStyledButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStaff();
            }
        });
        buttonPanel.add(deleteButton);

        JButton viewButton = createStyledButton("View");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewStaff();
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

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Staff Records"));
        tablePanel.setBackground(new Color(255, 255, 255)); // White background

        tableModel = new DefaultTableModel(new String[]{"Staff ID", "Position", "Salary"}, 0);
        staffTable = new JTable(tableModel);
        staffTable.setRowHeight(25); // Increase row height for better readability
        JScrollPane scrollPane = new JScrollPane(staffTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Move table panel to the center
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Add button panel to the bottom of the layout
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to the frame
        add(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(100, 40)); // Comfortable button size
        button.setBackground(new Color(70, 130, 180)); // Steel blue background
        button.setForeground(Color.BLACK); // White text
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font for better visibility
        return button;
    }

    private void addStaff() {
        String id = idField.getText().trim();
        String position = positionField.getText().trim();
        String salary = salaryField.getText().trim();

        if (id.isEmpty() || position.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO staff (staff_id, position, salary) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.setString(2, position);
            stmt.setDouble(3, Double.parseDouble(salary));
            stmt.executeUpdate();

            tableModel.addRow(new Object[]{id, position, salary});
            clearFields();
            JOptionPane.showMessageDialog(this, "Staff added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding staff.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editStaff() {
        int selectedRow = staffTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a staff record to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = idField.getText().trim();
        String position = positionField.getText().trim();
        String salary = salaryField.getText().trim();

        if (id.isEmpty() || position.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE staff SET position = ?, salary = ? WHERE staff_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, position);
            stmt.setDouble(2, Double.parseDouble(salary));
            stmt.setInt(3, Integer.parseInt(id));
            stmt.executeUpdate();

            tableModel.setValueAt(id, selectedRow, 0);
            tableModel.setValueAt(position, selectedRow, 1);
            tableModel.setValueAt(salary, selectedRow, 2);
            clearFields();
            JOptionPane.showMessageDialog(this, "Staff record updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating staff.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStaff() {
        int selectedRow = staffTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a staff record to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM staff WHERE staff_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();

            tableModel.removeRow(selectedRow);
            clearFields();
            JOptionPane.showMessageDialog(this, "Staff record deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting staff.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewStaff() {
        int selectedRow = staffTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a staff record to view.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM staff WHERE staff_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String position = rs.getString("position");
                String salary = String.valueOf(rs.getDouble("salary"));
                JOptionPane.showMessageDialog(this, "Staff Details:\nID: " + id + "\nPosition: " + position + "\nSalary: " + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error viewing staff.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        positionField.setText("");
        salaryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageStaffForm().setVisible(true));
    }
}
