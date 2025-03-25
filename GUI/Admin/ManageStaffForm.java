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
    private JTextField idField, salaryField;
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
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Staff Details"));
        inputPanel.setBackground(new Color(255, 255, 255)); // White background

        inputPanel.add(new JLabel("Staff ID:"));
        idField = new JTextField(15);
        inputPanel.add(idField);

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

        tableModel = new DefaultTableModel(new String[]{"Staff ID", "Name", "Sex", "Position", "Salary"}, 0);
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
        String salary = salaryField.getText().trim();

        if (id.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE staff SET salary = ? WHERE staff_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, Double.parseDouble(salary));
            stmt.setInt(2, Integer.parseInt(id));
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Salary updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Staff ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating salary.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editStaff() {
        int selectedRow = staffTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a staff record to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = idField.getText().trim();
        String salary = salaryField.getText().trim();

        if (id.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE staff SET salary = ? WHERE staff_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, Double.parseDouble(salary));
            stmt.setInt(2, Integer.parseInt(id));
            stmt.executeUpdate();

            tableModel.setValueAt(id, selectedRow, 0);
            tableModel.setValueAt(salary, selectedRow, 4);
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
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT staff.staff_id, user.name, user.sex, staff.position, staff.salary " +
                         "FROM staff INNER JOIN user ON staff.email = user.email";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0); // Clear existing rows
            while (rs.next()) {
                int id = rs.getInt("staff_id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String position = rs.getString("position");
                double salary = rs.getDouble("salary");

                tableModel.addRow(new Object[]{id, name, sex, position, salary});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving staff data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        salaryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageStaffForm().setVisible(true));
    }
}
