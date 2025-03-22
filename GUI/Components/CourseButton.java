package GUI.Components;
import javax.swing.*;
import models.*;
import GUI.AttendanceGUI;
import models.Student;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.AttendanceSubmissionGUI;

public class CourseButton extends JButton {

    public CourseButton(String courseName,Student student,int courseId,String schedule,String classroom) {
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
    public CourseButton(String courseName, ActionListener actionListener) {
        super(courseName); // Set button text
        setFont(new Font("Arial", Font.PLAIN, 16));

        // Add the provided ActionListener
        if (actionListener != null) {
            addActionListener(actionListener);
        }
    }
    public CourseButton(String textButton,String classroom,String schedule,Student student,int courseId){
        super("<html><center><u>" + textButton + "</u><br>Room: " + classroom + "<br>Time: " + schedule + "</center></html>");
        setFont(new Font("arial", Font.PLAIN, 16));
        setFocusPainted(false);
        setPreferredSize(new Dimension(350, 60));
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AttendanceSubmissionGUI(student, courseId);
            }
        });
    }
    public static void main(String[] args) {
        
    }
}

