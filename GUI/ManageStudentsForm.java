package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageStudentsForm extends JFrame {
    private JTextField idField, nameField, schoolFeeField; // Updated field declarations
    private JTable studentTable;
    private DefaultTableModel tableModel;

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
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 20, 10));
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

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Student Records"));
        tablePanel.setBackground(new Color(255, 255, 255)); // White background

        tableModel = new DefaultTableModel(new String[]{"Student ID", "Name", "School Fee"}, 0); // Updated table columns
        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(25); // Increase row height for better readability
        JScrollPane scrollPane = new JScrollPane(studentTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.SOUTH);

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

        tableModel.addRow(new Object[]{id, name, schoolFee}); // Updated row data
        clearFields();
        JOptionPane.showMessageDialog(this, "Student added successfully.");
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

        tableModel.setValueAt(id, selectedRow, 0);
        tableModel.setValueAt(name, selectedRow, 1);
        tableModel.setValueAt(schoolFee, selectedRow, 2); // Updated column index
        clearFields();
        JOptionPane.showMessageDialog(this, "Student record updated successfully.");
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student record to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.removeRow(selectedRow);
        clearFields();
        JOptionPane.showMessageDialog(this, "Student record deleted successfully.");
    }

    private void viewStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student record to view.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();
        String name = tableModel.getValueAt(selectedRow, 1).toString();
        String schoolFee = tableModel.getValueAt(selectedRow, 2).toString(); // Updated column index

        JOptionPane.showMessageDialog(this, "Student Details:\nID: " + id + "\nName: " + name + "\nSchool Fee: " + schoolFee); // Updated message
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
