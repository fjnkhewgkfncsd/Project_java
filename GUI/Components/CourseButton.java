package GUI.Components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseButton extends JButton {

    public CourseButton(String courseName) {
        super(courseName); // Set button text
        setFont(new Font("Arial", Font.PLAIN, 16));// Width: 150px, Height: 50px
        // Add click event
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCoursePage(courseName);
            }
        });
    }
    public CourseButton(String textButton,String classroom,String schedule) {
        super("<html><center><u>" + textButton + "</u><br>Room: " + classroom + "<br>Time: " + schedule + "</center></html>");
        setFont(new Font("arial", Font.PLAIN, 16));
    }
    private void openCoursePage(String courseName) {
        JFrame courseFrame = new JFrame(courseName + " - Details");
        courseFrame.setSize(400, 300);
        courseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        courseFrame.setLocationRelativeTo(null);
        JLabel label = new JLabel("Welcome to " + courseName, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        courseFrame.add(label);
        courseFrame.setVisible(true);
    }
}

