package com.example.UI;

import javax.swing.*;

import com.example.DAL.Student;
import com.example.DAL.StudentDAL;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class StudentUI {
    private JFrame frame;
    private JTable table;
    private StudentDAL studentDAL;

    public StudentUI() {
        // Initialize the DAL
        studentDAL = new StudentDAL();

        // Set up the JFrame
        frame = new JFrame("Student Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create the JTable and populate it with data
        try {
            List<Student> students = studentDAL.readStudents();
            table = createTable(students);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    private JTable createTable(List<Student> students) {
        // Define column names (as shown in the screenshot)
        String[] columnNames = {"TT", "PersonID", "FirstName", "LastName", "EnrollmentDate"};

        // Convert the list of students into a 2D array for the JTable
        Object[][] data = new Object[students.size()][5];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = i + 1; // TT (row number)
            data[i][1] = student.getPersonId();
            data[i][2] = student.getFirstName();
            data[i][3] = student.getLastName();
            data[i][4] = student.getEnrollmentDate();
        }

        // Create and return the JTable
        JTable table = new JTable(data, columnNames);
        return table;
    }

    public static void main(String[] args) {
        // Run the UI on the Event Dispatch Thread (EDT) for thread safety
        SwingUtilities.invokeLater(() -> new StudentUI());
    }
}