package GUI.Components;
import javax.swing.*;

import GUI.AttendanceGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseButton extends JButton {

    public CourseButton(String courseName) {
        super(courseName); // Set button text
        setFont(new Font("Arial", Font.PLAIN, 16));
        // Add click event
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AttendanceGUI(courseName);
            }
        });
    }
    public CourseButton(String textButton,String classroom,String schedule) {
        super("<html><center><u>" + textButton + "</u><br>Room: " + classroom + "<br>Time: " + schedule + "</center></html>");
        setFont(new Font("arial", Font.PLAIN, 16));
        setFocusPainted(false);
        setPreferredSize(new Dimension(350, 60));
    }
    private void openCoursePage(String courseName) {
        JFrame courseFrame = new JFrame(courseName + " - Details");
        courseFrame.setSize(400, 300);
        courseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        courseFrame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Welcome to " + courseName, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        setFocusPainted(false);
        courseFrame.add(label);
        courseFrame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseButton("Math","A10","12:00-11:10").setVisible(true));
    }
}

