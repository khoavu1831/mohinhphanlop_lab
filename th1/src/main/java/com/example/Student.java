package com.example;

import java.sql.Date;

public class Student {
    String firstName, lastName;
    int personId;
    Date enrollmentDate;

    public Student() {
    }

    public Student(String firstName, String lastName, int personId, Date enrollmentDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personId = personId;
        this.enrollmentDate = enrollmentDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

}
