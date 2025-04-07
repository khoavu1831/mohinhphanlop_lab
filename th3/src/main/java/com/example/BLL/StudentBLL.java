package com.example.BLL;

import java.sql.SQLException;
import java.util.List;

import com.example.DAL.Student;
import com.example.DAL.StudentDAL;

public class StudentBLL {
    private StudentDAL studentDAL;

    public StudentBLL() {
        studentDAL = new StudentDAL();
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentDAL.readStudents();
    }

    public List<Student> searchStudents(String fullName) throws SQLException {
        return studentDAL.findStudent(fullName);
    }

    // Add more methods as needed (e.g., for insert, update, delete)
    public int addStudent(Student student) throws SQLException {
        return studentDAL.insertStudent(student);
    }

    public int updateStudent(Student student) throws SQLException {
        return studentDAL.updateStudent(student);
    }

    public int deleteStudent(int personId) throws SQLException {
        return studentDAL.deleteStudent(personId);
    }
}