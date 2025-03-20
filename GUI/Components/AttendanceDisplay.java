package GUI.Components;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import models.StudentAttendance;

public class AttendanceDisplay extends JPanel {
    private JTextArea attendanceArea;

    public AttendanceDisplay(List<StudentAttendance> attendanceList) {
        setLayout(new BorderLayout());

        // Create and configure the JTextArea
        attendanceArea = new JTextArea();
        attendanceArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        attendanceArea.setEditable(false);
        attendanceArea.setMargin(new Insets(10, 10, 10, 10));

        // Populate the JTextArea with attendance data
        populateAttendanceArea(attendanceList);

        // Add the JTextArea to a JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane(attendanceArea);
        scrollPane.setPreferredSize(new Dimension(800, 400));

        // Add the scroll pane to the panel
        add(scrollPane, BorderLayout.CENTER);
    }

    // Method to populate the JTextArea with attendance data
    private void populateAttendanceArea(List<StudentAttendance> attendanceList) {
        StringBuilder sb = new StringBuilder();
        for (StudentAttendance record : attendanceList) {
            sb.append(record).append("\n");
        }
        attendanceArea.setText(sb.toString());
    }
}