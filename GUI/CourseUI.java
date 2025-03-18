package GUI;
import javax.swing.*;
import java.awt.*;
import GUI.Components.CourseButton;

public class CourseUI extends JFrame {
    public CourseUI() {
        setTitle("Courses");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Hello Student, welcome to CADT GEN X Major Class", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel coursePanel = new JPanel(new GridLayout(3, 2, 20, 20)); // 3 rows, 2 columns
        coursePanel.add(new CourseButton("Math"));
        coursePanel.add(new CourseButton("Physics"));
        coursePanel.add(new CourseButton("Java"));
        coursePanel.add(new CourseButton("C++"));
        coursePanel.add(new CourseButton("Databases"));
        coursePanel.add(new CourseButton("Web Development"));

        add(header, BorderLayout.NORTH);
        add(coursePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseUI());
    }
}
