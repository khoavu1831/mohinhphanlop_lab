package com.example.DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAL extends MyDatabaseManager {
    public StudentDAL() {
        super();
        this.connectDB();
    }

    // Modified to return a List<Student> instead of printing to console
    public List<Student> readStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Person WHERE EnrollmentDate > 0";
        ResultSet rs = this.doReadQuery(query);
        if (rs != null) {
            while (rs.next()) {
                Student student = new Student();
                student.setPersonId(rs.getInt("PersonID"));
                student.setFirstName(rs.getString("FirstName"));
                student.setLastName(rs.getString("LastName"));
                student.setEnrollmentDate(rs.getDate("EnrollmentDate"));
                students.add(student);
            }
        }
        return students;
    }

    public int updateStudent(Student s) throws SQLException {
        String query = "UPDATE Person SET FirstName = ?, LastName = ? WHERE PersonID = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, s.getFirstName());
        p.setString(2, s.getLastName());
        p.setInt(3, s.getPersonId());
        int result = p.executeUpdate();
        return result;
    }

    public int insertStudent(Student s) throws SQLException {
        String query = "INSERT INTO Person (FirstName, LastName, EnrollmentDate) VALUES (?, ?, ?)";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, s.getFirstName());
        p.setString(2, s.getLastName());
        p.setDate(3, s.getEnrollmentDate()); // Fixed: Use setDate instead of setString
        int result = p.executeUpdate();
        return result;
    }

    public void findStudent(String fullName) throws SQLException {
        String query = "SELECT * FROM Person WHERE CONCAT(FirstName, ' ', LastName) LIKE ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, "%" + fullName + "%");
        ResultSet rs = p.executeQuery();
        if (rs != null) {
            int i = 1;
            while (rs.next()) {
                System.out.print(i + " - ");
                System.out.println(rs.getString("LastName") + " " + rs.getString("FirstName"));
                i++;
            }
        } else {
            System.out.println("Not found");
        }
    }

    public int deleteStudent(int personID) throws SQLException {
        String query = "DELETE FROM Person WHERE PersonID = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, personID);
        int result = p.executeUpdate();
        return result;
    }

    public static void main(String[] args) {
        // Test the readStudents method
        try {
            StudentDAL dal = new StudentDAL();
            List<Student> students = dal.readStudents();
            for (Student s : students) {
                System.out.println(s.getPersonId() + "\t" + s.getFirstName() + "\t" + s.getLastName() + "\t" + s.getEnrollmentDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}