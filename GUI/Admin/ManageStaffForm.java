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

    public ManageStaffForm() {
        setTitle("Manage Staff");
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

        JLabel titleLabel = new JLabel("Manage Staff", JLabel.CENTER);
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
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Staff Details"));
        inputPanel.setBackground(new Color(255, 255, 255));

        inputPanel.add(new JLabel("Staff ID:"));
        idField = new JTextField(15);
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField(15);
        inputPanel.add(salaryField);

        return inputPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Staff Records"));
        tablePanel.setBackground(new Color(255, 255, 255));

        tableModel = new DefaultTableModel(new String[]{"Staff ID", "Name", "Sex", "Position", "Salary"}, 0);
        staffTable = new JTable(tableModel);
        staffTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(staffTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton addButton = createStyledButton("Add");
        addButton.addActionListener(e -> addStaff());
        buttonPanel.add(addButton);

        JButton editButton = createStyledButton("Edit");
        editButton.addActionListener(e -> editStaff());
        buttonPanel.add(editButton);

        JButton deleteButton = createStyledButton("Delete");
        deleteButton.addActionListener(e -> deleteStaff());
        buttonPanel.add(deleteButton);

        JButton viewButton = createStyledButton("View");
        viewButton.addActionListener(e -> viewStaff());
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

    private void addStaff() {
        String id = idField.getText().trim();
        String salary = salaryField.getText().trim();

        if (id.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if staff_id exists
            String checkSql = "SELECT COUNT(*) FROM staff WHERE staff_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, Integer.parseInt(id));
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                // Update salary if staff_id exists
                String updateSql = "UPDATE staff SET salary = ? WHERE staff_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setDouble(1, Double.parseDouble(salary));
                updateStmt.setInt(2, Integer.parseInt(id));
                updateStmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Salary updated successfully.");
            } else {
                // Insert new staff record if staff_id does not exist
                String insertSql = "INSERT INTO staff (staff_id, salary) VALUES (?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, Integer.parseInt(id));
                insertStmt.setDouble(2, Double.parseDouble(salary));
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "New staff added successfully.");
            }
            clearFields();
            viewStaff(); // Refresh the table to display the updated data
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding/updating staff.", "Error", JOptionPane.ERROR_MESSAGE);
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
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sql = "SELECT * from staff";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0); // Clear existing rows
            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                int id = rs.getInt("staff_id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String position = rs.getString("position");
                double salary = rs.getDouble("salary");

                tableModel.addRow(new Object[]{id, name, sex, position, salary});
            }

            if (!hasData) {
                JOptionPane.showMessageDialog(this, "No staff records found.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving staff data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        salaryField.setText("");
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> new ManageStaffForm().setVisible(true));
    // }
}
