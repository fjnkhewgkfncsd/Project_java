package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageLecturesForm extends JFrame {
    private JTextField lectureIdField, courseField, salaryField;
    private JTable lectureTable;
    private DefaultTableModel tableModel;

    public ManageLecturesForm() {
        setTitle("Manage Lectures");
        setSize(1000, 700); // Adjusted size for better layout
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set background color
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Lecture Details"));
        inputPanel.setBackground(new Color(255, 255, 255)); // White background

        inputPanel.add(new JLabel("Lecture ID:"));
        lectureIdField = new JTextField(15);
        inputPanel.add(lectureIdField);

        inputPanel.add(new JLabel("Course:"));
        courseField = new JTextField(15);
        inputPanel.add(courseField);

        inputPanel.add(new JLabel("Salary:"));
        salaryField = new JTextField(15);
        inputPanel.add(salaryField);

        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Match main panel background

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
                viewLecture();
            }
        });
        buttonPanel.add(viewButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Lecture Records"));
        tablePanel.setBackground(new Color(255, 255, 255)); // White background

        tableModel = new DefaultTableModel(new String[]{"Lecture ID", "Course", "Salary"}, 0);
        lectureTable = new JTable(tableModel);
        lectureTable.setRowHeight(25); // Increase row height for better readability
        JScrollPane scrollPane = new JScrollPane(lectureTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.SOUTH);

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

    private void addLecture() {
        String lectureId = lectureIdField.getText().trim();
        String course = courseField.getText().trim();
        String salary = salaryField.getText().trim();

        if (lectureId.isEmpty() || course.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.addRow(new Object[]{lectureId, course, salary});
        clearFields();
        JOptionPane.showMessageDialog(this, "Lecture added successfully.");
    }

    private void editLecture() {
        int selectedRow = lectureTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a lecture record to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String lectureId = lectureIdField.getText().trim();
        String course = courseField.getText().trim();
        String salary = salaryField.getText().trim();

        if (lectureId.isEmpty() || course.isEmpty() || salary.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.setValueAt(lectureId, selectedRow, 0);
        tableModel.setValueAt(course, selectedRow, 1);
        tableModel.setValueAt(salary, selectedRow, 2);
        clearFields();
        JOptionPane.showMessageDialog(this, "Lecture record updated successfully.");
    }

    private void deleteLecture() {
        int selectedRow = lectureTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a lecture record to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        tableModel.removeRow(selectedRow);
        clearFields();
        JOptionPane.showMessageDialog(this, "Lecture record deleted successfully.");
    }

    private void viewLecture() {
        int selectedRow = lectureTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a lecture record to view.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String lectureId = tableModel.getValueAt(selectedRow, 0).toString();
        String course = tableModel.getValueAt(selectedRow, 1).toString();
        String salary = tableModel.getValueAt(selectedRow, 2).toString();

        JOptionPane.showMessageDialog(this, "Lecture Details:\nLecture ID: " + lectureId + "\nCourse: " + course + "\nSalary: " + salary);
    }

    private void clearFields() {
        lectureIdField.setText("");
        courseField.setText("");
        salaryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageLecturesForm().setVisible(true));
    }
}
