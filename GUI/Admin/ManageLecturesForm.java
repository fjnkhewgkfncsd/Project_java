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

public class ManageLecturesForm extends JFrame {
    private JTextField idField, salaryField;
    private JTable lectureTable;
    private DefaultTableModel tableModel;
    private JButton backButton;

    public ManageLecturesForm() {
        setTitle("Manage Lectures");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Lecture Details"));
        inputPanel.setBackground(new Color(255, 255, 255));

        inputPanel.add(new JLabel("Lecture ID:"));
        idField = new JTextField(15);
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField(15);
        inputPanel.add(salaryField);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton addButton = createStyledButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLecture();
            }
        });
        buttonPanel.add(addButton);

        JButton editButton = createStyledButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editLecture();
            }
        });
        buttonPanel.add(editButton);

        JButton deleteButton = createStyledButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLecture();
            }
        });
        buttonPanel.add(deleteButton);

        JButton viewButton = createStyledButton("View");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewLectures();
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
        tablePanel.setBorder(BorderFactory.createTitledBorder("Lecture Records"));
        tablePanel.setBackground(new Color(255, 255, 255));

        tableModel = new DefaultTableModel(new String[]{"Lecture ID", "Name", "Sex", "Specialization", "Salary"}, 0);
        lectureTable = new JTable(tableModel);
        lectureTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(lectureTable);
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

    private void addLecture() {
        String id = idField.getText().trim();
        String salary = salaryField.getText().trim();

        if (id.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE lecturer SET salary = ? WHERE lecturer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, Double.parseDouble(salary));
            stmt.setInt(2, Integer.parseInt(id));
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Salary updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Lecture ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating salary.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editLecture() {
        int selectedRow = lectureTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a lecture record to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = idField.getText().trim();
        String salary = salaryField.getText().trim();

        if (id.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE lecturer SET salary = ? WHERE lecturer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, Double.parseDouble(salary));
            stmt.setInt(2, Integer.parseInt(id));
            stmt.executeUpdate();

            tableModel.setValueAt(id, selectedRow, 0);
            tableModel.setValueAt(salary, selectedRow, 4);
            clearFields();
            JOptionPane.showMessageDialog(this, "Lecture record updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating lecture.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteLecture() {
        int selectedRow = lectureTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a lecture record to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM lecturer WHERE lecturer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();

            tableModel.removeRow(selectedRow);
            clearFields();
            JOptionPane.showMessageDialog(this, "Lecture record deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting lecture.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewLectures() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT lecturer.lecturer_id, user.name, user.sex, lecturer.specialization, lecturer.salary " +
                         "FROM lecturer INNER JOIN user ON lecturer.email = user.email";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0);
            while (rs.next()) {
                int id = rs.getInt("lecturer_id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String specialization = rs.getString("specialization");
                double salary = rs.getDouble("salary");

                tableModel.addRow(new Object[]{id, name, sex, specialization, salary});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving lecture data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setText("");
        salaryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageLecturesForm().setVisible(true));
    }
}
