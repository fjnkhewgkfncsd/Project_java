package GUI.Components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonGUI extends JFrame {
    public ButtonGUI() {
        setTitle("Button Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout()); // Use FlowLayout to respect button size

        JButton myButton = new JButton("Click Me");
        myButton.setPreferredSize(new Dimension(150, 50)); // Set button size
        myButton.setFont(new Font("Arial", Font.BOLD, 16));

        myButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Button Clicked!");
            }
        });
        add(myButton);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ButtonGUI());
    }
}