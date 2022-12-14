package com.example.distributedsystemssoap.repository;

import io.spring.guides.gs_producing_web_service.Student;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class StudentRepository {
    private final DataAccessObject dao = new DataAccessObject();

    @PreDestroy
    public void onDestroy() {
        dao.closeConnection();
    }

    public Student findStudentById(long id) {
        return dao.findStudentById(id)
                .orElseThrow(() -> new IllegalStateException("Student not found"));
    }

    public void createNewStudent(Student student) {
        dao.createNewStudent(student);
    }

    public void updateStudent(Student student) {
        dao.updateStudent(student);
    }

    public void deleteStudent(long id) {
        dao.deleteStudent(id);
    }
}
