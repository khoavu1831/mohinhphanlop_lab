package com.example.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.example.BLL.StudentBLL;
import com.example.DAL.Student;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class StudentUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton refreshButton; // New Refresh button
    private StudentBLL studentBLL;

    public StudentUI() {
        // Initialize the BLL
        studentBLL = new StudentBLL();

        // Set up the JFrame
        frame = new JFrame("Student Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create the search panel (North)
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        refreshButton = new JButton("Refresh"); // Initialize the Refresh button
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton); // Add the Refresh button to the panel

        // Create the JTable and populate it with data
        try {
            List<Student> students = studentBLL.getAllStudents();
            table = createTable(students);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // Add the table to a scroll pane (Center)
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(searchPanel, BorderLayout.NORTH);

        // Add action listener for the search button
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim();
            try {
                List<Student> students;
                if (searchText.isEmpty()) {
                    students = studentBLL.getAllStudents();
                } else {
                    students = studentBLL.searchStudents(searchText);
                }
                updateTable(students);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error searching data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add action listener for the refresh button
        refreshButton.addActionListener(e -> {
            try {
                List<Student> students = studentBLL.getAllStudents();
                updateTable(students);
                searchField.setText(""); // Clear the search field on refresh
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error refreshing data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }

    private JTable createTable(List<Student> students) {
        // Define column names
        String[] columnNames = {"TT", "PersonID", "FirstName", "LastName", "EnrollmentDate"};

        // Create a DefaultTableModel
        tableModel = new DefaultTableModel(columnNames, 0);

        // Populate the model with data
        updateTable(students);

        // Create and return the JTable with the model
        JTable table = new JTable(tableModel);
        return table;
    }

    private void updateTable(List<Student> students) {
        // Clear the existing rows
        tableModel.setRowCount(0);

        // Populate the model with new data
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            Object[] row = new Object[]{
                i + 1, // TT
                student.getPersonId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEnrollmentDate()
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        // Run the UI on the Event Dispatch Thread (EDT) for thread safety
        SwingUtilities.invokeLater(() -> new StudentUI());
    }
}