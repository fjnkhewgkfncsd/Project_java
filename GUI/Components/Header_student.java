package GUI.Components;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header_student extends JPanel {
    public Header_student(String name, int gen, String major) {
        // Set layout for the panel
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(30, 0, 50, 0)); // Add padding around the panel

        // Create the "Hello" text label
        JLabel helloLabel = new JLabel("Hello " + name + "! Welcome To CADT Gen " + gen + " " + major);
        helloLabel.setFont(new Font("Arial", Font.BOLD, 34));
        helloLabel.setAlignmentX(CENTER_ALIGNMENT); // Center horizontally
        helloLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Add padding below the label

        // Create the "Course" text label
        JLabel courseLabel = new JLabel("Courses");
        courseLabel.setFont(new Font("Arial", Font.PLAIN, 32));
        courseLabel.setAlignmentX(CENTER_ALIGNMENT); // Center horizontally

        // Add both labels to the panel
        add(helloLabel);
        add(courseLabel);
    }
    public static void main(String[] args) {
        // JFrame frame = new JFrame();
        // frame.setSize(500, 300);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Header_student header = new Header_student("Mengly", 15, "Software Engineering");
        // frame.add(header);

        // frame.setVisible(true);
            
        }
}

