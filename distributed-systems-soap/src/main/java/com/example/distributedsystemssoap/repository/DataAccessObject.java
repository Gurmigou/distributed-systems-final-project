package com.example.distributedsystemssoap.repository;

import io.spring.guides.gs_producing_web_service.Group;
import io.spring.guides.gs_producing_web_service.Student;

import java.sql.*;
import java.util.Optional;

public class DataAccessObject {
    private static final String URL = "jdbc:mysql://localhost:3306/lab_9";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private final Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath", e);
        }
    }

    public DataAccessObject() {
        this.connection = connectToDatabase();
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connectToDatabase() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewGroup(Group group) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `group` (name) VALUES (?)");
            ps.setString(1, group.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNewStudent(Student student) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO student (first_name, getLastName(), group_id) VALUES (?, ?, ?)");
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setLong(3, student.getGroupId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteGroup(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `group` WHERE id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStudent(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM student WHERE id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateGroup(Group group) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE `group` SET name = ? WHERE id = ?");
            ps.setString(1, group.getName());
            ps.setLong(2, group.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStudent(Student student) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE student SET first_name = ?, getLastName() = ?, group_id = ? WHERE id = ?");
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setLong(3, student.getGroupId());
            ps.setLong(4, student.getGroupId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Group> findGroupById(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `group` WHERE id = ?");
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getLong("id"));
                group.setName(resultSet.getString("name"));
                return Optional.of(group);
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Student> findStudentById(long id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setFirstName(resultSet.getString("fistName"));
                student.setLastName(resultSet.getString("lastName"));
                student.setGroupId(resultSet.getLong("groupId"));
                return Optional.of(student);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
