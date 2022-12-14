package com.example.distributedsystemsrest.model;

import java.io.Serializable;

public class Student implements Serializable {
    public int id;
    public String firstName;
    public String lastName;
    public Group group;

    public Student(int id, String firstName, String lastName, Group group) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", group=" + group +
                '}';
    }
}