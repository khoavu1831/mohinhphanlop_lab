package com.example;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAL extends MyDatabaseManager {
    public StudentDAL() {
        super();
        this.connectDB();
    }

    public void readStudents() throws SQLException {
        String query = "SELECT * FROM Person WHERE EnrollmentDate >0";
        ResultSet rs = this.doReadQuery(query);
        if (rs != null) {
            int i = 1;
            System.out.println("\nTT \t PersonID \t FirstName \t\t LastName");
            while (rs.next()) {
                System.out.print(i + "\t" + rs.getInt("PersonID"));
                System.out.println("\t\t" + rs.getString("FirstName") + "\t\t\t" + rs.getString("LastName"));
                i++;
            }
        }
    }

    public int updateStudent(Student s) throws SQLException {
        String query = "Update Person SET FirstName = ? , LastName = ? WHERE PersonID = ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, s.getFirstName());
        p.setString(2, s.getLastName());
        p.setInt(3, s.getPersonId());
        int result = p.executeUpdate();
        return result;
    }

    public int insertStudent(Student s) throws SQLException {
        String query = "Insert Person (FirstName, LastName, EnrollmentDate) VALUES (?, ?, ?)";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, s.getFirstName());
        p.setString(2, s.getLastName());
        p.setDate(3, s.getEnrollmentDate()); // Use setDate instead of setString
        int result = p.executeUpdate();
        return result;
    }

    public void findStudent(String fullName) throws SQLException {
        String query = "SELECT * FROM Person WHERE concat(FirstName, ' ', LastName)  LIKE ?";
        PreparedStatement p = this.getConnection().prepareStatement(query);
        p.setString(1, "%" + fullName + "%");
        ResultSet rs = p.executeQuery();
        if (rs != null) {
            int i = 1;
            while (rs.next()) {
                System.out.print(i + " - ");
                System.out.println(rs.getString("Lastname") + " " + rs.getString("Firstname"));
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
        // this.readStudents();
        return result;
    }

    public static void main(String[] args) {
        try {
            StudentDAL dal = new StudentDAL();
            dal.readStudents(); 
            dal.findStudent("Roger Holt");
            dal.findStudent("Cody Rogers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}