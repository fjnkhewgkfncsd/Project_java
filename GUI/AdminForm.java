package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminForm extends JFrame {
    private JButton manageStaffButton, manageStudentsButton, manageCoursesButton, manageLecturesButton;

    public AdminForm() {
        setTitle("Admin Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the form full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        manageStaffButton = new JButton("Manage Staff");
        manageStudentsButton = new JButton("Manage Students");
        manageCoursesButton = new JButton("Manage Courses");
        manageLecturesButton = new JButton("Manage Lectures");

        // Set font size for the buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        manageStaffButton.setFont(buttonFont);
        manageStudentsButton.setFont(buttonFont);
        manageCoursesButton.setFont(buttonFont);
        manageLecturesButton.setFont(buttonFont);

        // Set preferred size for the buttons
        Dimension buttonSize = new Dimension(300, 100);
        manageStaffButton.setPreferredSize(buttonSize);
        manageStudentsButton.setPreferredSize(buttonSize);
        manageCoursesButton.setPreferredSize(buttonSize);
        manageLecturesButton.setPreferredSize(buttonSize);

        panel.add(manageStaffButton);
        panel.add(manageStudentsButton);
        panel.add(manageCoursesButton);
        panel.add(manageLecturesButton);

        add(panel);

        manageStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open staff management form
                new ManageStaffForm().setVisible(true);
                dispose();
            }
        });

        manageStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open student management form
                new ManageStudentsForm().setVisible(true);
                dispose();
            }
        });

        manageCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open course management form
                new ManageCoursesForm().setVisible(true);
                dispose();
            }
        });

        manageLecturesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open lecture management form
                new ManageLecturesForm().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminForm().setVisible(true));
    }
}
