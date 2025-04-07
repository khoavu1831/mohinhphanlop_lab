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
        p.setDate(3, s.getEnrollmentDate());
        int result = p.executeUpdate();
        return result;
    }

    // Modified to return a List<Student> instead of printing
    public List<Student> findStudent(String fullName) throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM Person WHERE CONCAT(FirstName, ' ', LastName) LIKE ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, "%" + fullName + "%");
        ResultSet rs = p.executeQuery();
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

    public int deleteStudent(int personID) throws SQLException {
        String query = "DELETE FROM Person WHERE PersonID = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setInt(1, personID);
        int result = p.executeUpdate();
        return result;
    }
}