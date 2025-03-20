package GUI.Components;
import javax.swing.*;

import GUI.AttendanceGUI;
import models.Student;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseButton extends JButton {

    public CourseButton(String courseName,Student student,String courseId) {
        super(courseName); // Set button text
        setFont(new Font("Arial", Font.PLAIN, 16));
        // Add click event
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AttendanceGUI(courseName,student,courseId);
            }
        });
    }
    //view attendance 
    public CourseButton(String courseName, ActionListener actionListener,Student student) {
        super(courseName); // Set button text
        setFont(new Font("Arial", Font.PLAIN, 16));

        // Add the provided ActionListener
        if (actionListener != null) {
            addActionListener(actionListener);
        }
    }
    public CourseButton(String textButton,String classroom,String schedule) {
        super("<html><center><u>" + textButton + "</u><br>Room: " + classroom + "<br>Time: " + schedule + "</center></html>");
        setFont(new Font("arial", Font.PLAIN, 16));
        setFocusPainted(false);
        setPreferredSize(new Dimension(350, 60));
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseButton("Math","A10","12:00-11:10").setVisible(true));
    }
}

